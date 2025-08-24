package io.github.itskillerluc.alternacraft.entity;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.client.model.MagmatyrannusModel;
import io.github.itskillerluc.duclib.client.animation.DucAnimation;
import io.github.itskillerluc.duclib.entity.Animatable;
import io.github.itskillerluc.alternacraft.entity.ai.BreathAttackGoal;
import io.github.itskillerluc.alternacraft.entity.ai.SleepingPattern;
import io.github.itskillerluc.alternacraft.init.EntityDataSerailizerRegistry;
import io.github.itskillerluc.alternacraft.init.EntityRegistry;
import io.github.itskillerluc.alternacraft.init.Tags;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Magmatyrannus extends DinoEntity<Magmatyrannus> implements Animatable<MagmatyrannusModel>, VariantHolder<Magmatyrannus.Variant> {
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magmatyrannus");
    public static final DucAnimation ANIMATION = DucAnimation.create(LOCATION);

    public static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(Magmatyrannus.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> BREATHING_FIRE = SynchedEntityData.defineId(Magmatyrannus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Variant> VARIANT = SynchedEntityData.defineId(Magmatyrannus.class, EntityDataSerailizerRegistry.MAGMA_TYRANNUS_VARIANT_SERIALIZER.get());

    private final Lazy<Map<String, AnimationState>> animations = Lazy.of(() -> MagmatyrannusModel.createStateMap(getAnimation()));
    private final DinoPart<Magmatyrannus> head;
    private final List<DinoPart<Magmatyrannus>> subEntities;

    public Magmatyrannus(EntityType<? extends Magmatyrannus> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        head = new DinoPart<>(this, "head", 1.5F, 1.3F, 1.7f, new Vec3(4, 3.3, 4));
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
        pBuilder.define(BREATHING_FIRE, false);
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
        if (!Objects.equals(target, getTarget())) {
            level().broadcastEntityEvent(this, (byte) 2);
        }
        super.setTarget(target);
        entityData.set(RUNNING, target != null);
    }

    @Override
    int hungerDecreaseSpeed() {
        return 60;
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile();
    }

    @Override
    public DinoPart<Magmatyrannus> getHead() {
        return head;
    }

    @Override
    float sleepingOffset() {
        return isSleeping() ? 2 : 0;
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
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false) {
            @Override
            public boolean canUse() {
                return super.canUse() && distanceToSqr(getTarget()) < 55;
            }
        });
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
        goalSelector.addGoal(4, new BreathAttackGoal(this, 1, 1, ParticleTypes.FLAME, 65, 100, 300, 20, 1, 5, 30, 20, new Vec3(0, -0.4, 1), 30, 1, 1, entity -> entity.setRemainingFireTicks(240)) {
            @Override
            public boolean canUse() {
                return super.canUse() && distanceToSqr(getTarget()) >= 55;
            }
        });

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, entity -> entity.getType().is(Tags.EntityTypes.DINOS) || entity instanceof Player) {
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
        targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, entity -> entity.getType().getCategory() != MobCategory.WATER_CREATURE && entity.getType().getCategory() != MobCategory.WATER_AMBIENT ) {
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
            animateWhen("idle", !isMoving(this) && !isSleeping());
            animateWhen("sleep", isSleeping());
            if (random.nextFloat() < 0.005 && !isMoving(this) && !entityData.get(BREATHING_FIRE) && !isSleeping()) {
                if (random.nextFloat() < 0.25) {
                    replayAnimation("scratch");
                } else if (random.nextFloat() < 0.25) {
                    replayAnimation("sniff");
                } else if (random.nextFloat() < 0.25) {
                    replayAnimation("yawn");
                } else if (random.nextFloat() < 0.25) {
                    replayAnimation("look_around");
                }
            }
            animateWhen("breath_attack", entityData.get(BREATHING_FIRE));
        }
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return super.getDefaultDimensions(pose);
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
    public SleepingPattern getSleepingPattern() {
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
    public void setSleeping(boolean sleeping) {
        if (isSleeping()) {
            if (!sleeping) {
                dimensions = dimensions.scale(1f, 2f);
                level().broadcastEntityEvent(this, (byte) 0);
            }
        } else if (sleeping) {
            dimensions = dimensions.scale(1f, 0.5f);
            level().broadcastEntityEvent(this, (byte) 1);
        }
        super.setSleeping(sleeping);
    }

    @Override
    public void swing(InteractionHand hand) {
        super.swing(hand);
        if (level().isClientSide()) {
            replayAnimation("attack");
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 0) {
            dimensions = dimensions.scale(1f, 2f);
        } else if (id == 1) {
            dimensions = dimensions.scale(1f, 0.5f);
        } else if (id == 2) {
            replayAnimation("roar");
        }
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
