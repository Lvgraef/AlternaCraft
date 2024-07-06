package io.github.itskillerluc.entity;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.client.model.MagmatyrannusModel;
import io.github.itskillerluc.duclib.client.animation.DucAnimation;
import io.github.itskillerluc.duclib.entity.Animatable;
import io.github.itskillerluc.entity.ai.SleepingPattern;
import io.github.itskillerluc.init.EntityDataSerailizerRegistry;
import io.github.itskillerluc.init.EntityRegistry;
import io.github.itskillerluc.init.Tags;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Magmatyrannus extends DinoEntity<Magmatyrannus> implements Animatable<MagmatyrannusModel>, VariantHolder<Magmatyrannus.Variant> {
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magmatyrannus");
    public static final DucAnimation ANIMATION = DucAnimation.create(LOCATION);

    public static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(Magmatyrannus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Variant> VARIANT = SynchedEntityData.defineId(Magmatyrannus.class, EntityDataSerailizerRegistry.MAGMA_TYRANNUS_VARIANT_SERIALIZER.get());

    private final Lazy<Map<String, AnimationState>> animations = Lazy.of(() -> MagmatyrannusModel.createStateMap(getAnimation()));
    private final List<DinoPart<Magmatyrannus>> subEntities;

    public Magmatyrannus(EntityType<? extends Magmatyrannus> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        var head = new DinoPart<>(this, "head", 1.5F, 1.3F, 1.7f, new Vec3(4, 3.3, 4));
        var chest = new DinoPart<>(this, "body", 2.0F, 2.0F, 1.2f, new Vec3(2.1, 2.3, 2.1));
        var tail = new DinoPart<>(this, "tail", 2.0F, 2.0F, 0.8f, new Vec3(-2.1, 2.1, -2.1));
        var tailEnd = new DinoPart<>(this, "tail", 1.5F, 1.5F, 0.5f, new Vec3(-4.8, 2.5, -4.8));
        this.subEntities = List.of(head, chest, tail, tailEnd);
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.size() + 1) + 1);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, Variant.values()[random.nextInt(Variant.values().length)]);
        pBuilder.define(RUNNING, false);
    }

    @Nullable
    @Override
    public LivingEntity getLastHurtByMob() {
        return super.getLastHurtByMob() != null ? super.getLastHurtByMob() : lastHurtByPlayer;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return EntityRegistry.MAGMATYRANNUS.get().create(pLevel);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        entityData.set(RUNNING, target != null);
    }

    @Override
    int hungerDecreaseSpeed() {
        return 60;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("variant", entityData.get(VARIANT).ordinal());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        entityData.set(VARIANT, Variant.values()[pCompound.getInt("variant")]);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }

    public static AttributeSupplier.Builder attributes() {
        return AgeableMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.ATTACK_DAMAGE, 12D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 3);
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return AABB.ofSize(position(), 8, 8, 8);
    }

    @Override
    protected AABB getAttackBoundingBox() {
        Entity entity = this.getVehicle();
        AABB aabb;
        if (entity != null) {
            AABB aabb1 = entity.getBoundingBox();
            AABB aabb2 = getBoundingBoxForCulling();
            aabb = new AABB(
                    Math.min(aabb2.minX, aabb1.minX),
                    aabb2.minY,
                    Math.min(aabb2.minZ, aabb1.minZ),
                    Math.max(aabb2.maxX, aabb1.maxX),
                    aabb2.maxY,
                    Math.max(aabb2.maxZ, aabb1.maxZ)
            );
        } else {
            aabb = getBoundingBoxForCulling();
        }

        return aabb.inflate(0.8, 0.0, 0.8);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.005f) {
            @Nullable
            @Override
            protected Vec3 getPosition() {
                if (this.mob.isInWaterOrBubble()) {
                    Vec3 vec3 = LandRandomPos.getPos(this.mob, 30, 7);
                    return vec3 == null ? super.getPosition() : vec3;
                } else {
                    return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, 30, 7) : super.getPosition();
                }
            }
        });

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, entity -> entity.getType().is(Tags.EntityTypes.DINOS)) {
            @Override
            public boolean canUse() {
                targetConditions.range(getFollowDistance());
                return getHunger() < 100 && super.canUse();
            }
        });
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, entity -> entity instanceof Enemy) {
            @Override
            public boolean canUse() {
                targetConditions.range(getFollowDistance());
                return getHunger() < 50 && super.canUse();
            }
        });
        targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true) {
            @Override
            public boolean canUse() {
                targetConditions.range(getFollowDistance());
                return getHunger() < 25 && super.canUse();
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        var followRange = getAttributes().getInstance(Attributes.FOLLOW_RANGE);
        if (followRange != null) {
            if (getHunger() < 25) {
                followRange.setBaseValue(25);
            } else if (getHunger() < 50) {
                followRange.setBaseValue(10);
            } else {
                followRange.setBaseValue(3);
            }
            if (lastHurtByPlayerTime - tickCount() < 20 * 60) {
                followRange.setBaseValue(40);
            }
        }
        if (level().isClientSide) {
            animateWhen("idle", !isMoving(this));
        }
    }

    @Override
    public ResourceLocation getModelLocation() {
        return LOCATION;
    }

    @Override
    public DucAnimation getAnimation() {
        return ANIMATION;
    }

    @Override
    public Lazy<Map<String, AnimationState>> getAnimations() {
        return animations;
    }

    @Override
    public Optional<AnimationState> getAnimationState(String animation) {
        return Optional.ofNullable(getAnimations().get().get("animation.magmatyrannus." + animation));
    }

    @Override
    public int tickCount() {
        return tickCount;
    }

    @Override
    public void setVariant(Variant pVariant) {
        entityData.set(VARIANT, pVariant);
    }

    @Override
    public Variant getVariant() {
        return entityData.get(VARIANT);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pRemainingPersistentAngerTime) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pPersistentAngerTarget) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    @Override
    SleepingPattern getSleepingPattern() {
        return SleepingPattern.DIURNAL;
    }

    public List<DinoPart<Magmatyrannus>> getSubEntities() {
        return subEntities;
    }

    @Override
    public boolean killedEntity(ServerLevel pLevel, LivingEntity pEntity) {
        var killed = super.killedEntity(pLevel, pEntity);
        if (killed) {
            eatEnemy(pEntity);
        }
        return killed;
    }

    private void eatEnemy(LivingEntity entity) {
        if (entity.getType().is(Tags.EntityTypes.MINI_DINOS)) {
            feed(5);
        } else if (entity.getType().is(Tags.EntityTypes.SMALL_DINOS)) {
            feed(15);
        } else if (entity.getType().is(Tags.EntityTypes.MEDIUM_DINOS)) {
            feed(30);
        } else if (entity.getType().is(Tags.EntityTypes.LARGE_DINOS)) {
            feed(40);
        } else {
            feed(10);
        }
    }

    @Override
    public void swing(InteractionHand hand) {
        replayAnimation("attack");
        super.swing(hand);
    }

    public enum Variant {
        PINK(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "textures/entity/magmatyrannus_pink.png")),
        PURPLE(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "textures/entity/magmatyrannus_purple.png")),
        RED(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "textures/entity/magmatyrannus_red.png"));

        public static final StreamCodec<ByteBuf, Variant> STREAM_CODEC = ByteBufCodecs.idMapper(i -> Variant.values()[i], Enum::ordinal);
        private final ResourceLocation texture;


        Variant(ResourceLocation texture) {
            this.texture = texture;
        }

        public ResourceLocation getTexture() {
            return texture;
        }
    }
}
