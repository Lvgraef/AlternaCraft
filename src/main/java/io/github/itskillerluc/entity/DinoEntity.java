package io.github.itskillerluc.entity;

import io.github.itskillerluc.entity.ai.SleepingPattern;
import io.github.itskillerluc.util.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public abstract class DinoEntity<T extends DinoEntity<?>> extends TamableAnimal implements NeutralMob {
    private int maxHunger = hungerDecreaseSpeed() * 20 * 100;
    private int hunger = maxHunger;

    protected DinoEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    abstract int hungerDecreaseSpeed();

    @Override
    public void setId(int id) {
        super.setId(id);
        for (int i = 0; i < getSubEntities().size(); i++)
            getSubEntities().get(i).setId(id + i + 1);
    }


    @Override
    public void aiStep() {
        super.aiStep();
        for (DinoPart<T> subEntity : getSubEntities()) {
            setPartPos(subEntity, Math.sin(Math.toRadians(-getRotationVector().y)) * subEntity.getXOffset() + position().x,
                    position().y + subEntity.getYOffset(), Math.cos(Math.toRadians(-getRotationVector().y)) * subEntity.getZOffset() + position().z);
        }
    }

    private void setPartPos(DinoPart<T> part, double x, double y, double z) {
        part.setPos(x, y, z);
        part.xo = x;
        part.yo = y;
        part.zo = z;
        part.xOld = x;
        part.yOld = y;
        part.zOld = z;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("hunger", hunger);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("hunger")) {
            hunger = pCompound.getInt("hunger");
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public float getHunger() {
        return hunger / (hungerDecreaseSpeed() * 20f);
    }

    public void feed(float hunger) {
        this.hunger = (int) Math.min(maxHunger, this.hunger + (maxHunger/hunger));
    }

    @Override
    public void tick() {
        super.tick();
        if (hunger > 0) {
            hunger--;
        }
    }

    public boolean hurt(DinoPart<? extends DinoEntity> part, DamageSource source, float damage) {
        return super.hurt(source, damage * part.damageMultiplier);
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return getSubEntities().toArray(new PartEntity<?>[0]);
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    abstract SleepingPattern getSleepingPattern();
    abstract List<DinoPart<T>> getSubEntities();

    @Override
    public abstract AABB getBoundingBoxForCulling();

    @Override
    public void move(MoverType type, Vec3 pos) {
        super.move(type, pos);
        setOnGround(this.collide(pos).y == this.collide(getBoundingBox(), pos).y);
    }

    protected void pushEntities() {
        if (this.level().isClientSide()) {
            for (DinoPart<T> subEntity : getSubEntities()) {
                this.level().getEntities(EntityTypeTest.forClass(Player.class), subEntity.getBoundingBox(), EntitySelector.pushableBy(this)).forEach(this::doPush);
            }
            this.level().getEntities(EntityTypeTest.forClass(Player.class), this.getBoundingBox(), EntitySelector.pushableBy(this)).forEach(this::doPush);
        } else {
            List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), EntitySelector.pushableBy(this));
            for (DinoPart<T> subEntity : getSubEntities()) {
                list.addAll(this.level().getEntities(EntityTypeTest.forClass(Player.class), subEntity.getBoundingBox(), EntitySelector.pushableBy(this)));
            }
            if (!list.isEmpty()) {
                int i = this.level().getGameRules().getInt(GameRules.RULE_MAX_ENTITY_CRAMMING);
                if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
                    int j = 0;

                    for (Entity entity : list) {
                        if (!entity.isPassenger()) {
                            j++;
                        }
                    }

                    if (j > i - 1) {
                        this.hurt(this.damageSources().cramming(), 6.0F);
                    }
                }

                for (Entity entity1 : list) {
                    this.doPush(entity1);
                }
            }
        }
    }

    @Override
    protected Vec3 collide(Vec3 vec) {
        var mainCollision = collide(getBoundingBox(), vec);
        var sideCollisions = getSubEntities().stream().map(Entity::getBoundingBox).map(aabb -> collide(aabb, vec)).reduce((vec1, vec2) -> new Vec3(Util.closestToZero(vec1.x, vec2.x), Util.closestToZero(vec1.y, vec2.y), Util.closestToZero(vec1.z, vec2.z))).orElse(Vec3.ZERO);
        return new Vec3(Util.closestToZero(mainCollision.x, sideCollisions.x), mainCollision.y, Util.closestToZero(mainCollision.z, sideCollisions.z));
    }

    private Vec3 collide(AABB aabb, Vec3 vec) {
        List<VoxelShape> list = this.level().getEntityCollisions(this, aabb.expandTowards(vec));
        Vec3 vec3 = vec.lengthSqr() == 0.0 ? vec : collideBoundingBox(this, vec, aabb, this.level(), list);
        boolean flag = vec.x != vec3.x;
        boolean flag1 = vec.y != vec3.y;
        boolean flag2 = vec.z != vec3.z;
        boolean flag3 = flag1 && vec.y < 0.0;
        if (this.maxUpStep() > 0.0F && (flag3 || this.onGround()) && (flag || flag2)) {
            AABB aabb1 = flag3 ? aabb.move(0.0, vec3.y, 0.0) : aabb;
            AABB aabb2 = aabb1.expandTowards(vec.x, (double) this.maxUpStep(), vec.z);
            if (!flag3) {
                aabb2 = aabb2.expandTowards(0.0, -1.0E-5F, 0.0);
            }

            List<VoxelShape> list1 = collectColliders(this, level(), list, aabb2);
            float f = (float) vec3.y;
            float[] afloat = collectCandidateStepUpHeights(aabb1, list1, this.maxUpStep(), f);

            for (float f1 : afloat) {
                Vec3 vec31 = collideWithShapes(new Vec3(vec.x, (double) f1, vec.z), aabb1, list1);
                if (vec31.horizontalDistanceSqr() > vec3.horizontalDistanceSqr()) {
                    double d0 = aabb.minY - aabb1.minY;
                    return vec31.add(0.0, -d0, 0.0);
                }
            }
        }

        return vec3;
    }
}
