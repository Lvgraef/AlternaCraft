package io.github.itskillerluc.entity;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.client.model.CeratosaurusModel;
import io.github.itskillerluc.duclib.client.animation.DucAnimation;
import io.github.itskillerluc.duclib.entity.Animatable;
import io.github.itskillerluc.entity.ai.SleepingPattern;
import io.github.itskillerluc.init.EntityDataSerailizerRegistry;
import io.github.itskillerluc.init.EntityRegistry;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.Lazy;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Ceratosaurus extends DinoEntity<Ceratosaurus> implements Animatable<CeratosaurusModel>, VariantHolder<Ceratosaurus.Variant>, Enemy {
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "ceratosaurus");
    public static final DucAnimation ANIMATION = DucAnimation.create(LOCATION);

    public static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(Ceratosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Variant> VARIANT = SynchedEntityData.defineId(Ceratosaurus.class, EntityDataSerailizerRegistry.CERATOSAURUS_VARIANT_SERIALIZER.get());

    private final Lazy<Map<String, AnimationState>> animations = Lazy.of(() -> CeratosaurusModel.createStateMap(getAnimation()));
    private final DinoPart<Ceratosaurus> head;
    private final List<DinoPart<Ceratosaurus>> subEntities;

    public Ceratosaurus(EntityType<? extends Ceratosaurus> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        head = new DinoPart<>(this, "head", 0.8f, 0.7F, 1.7f, new Vec3(2.2, 1.6, 2.2));
        var chest = new DinoPart<>(this, "body", 0.8f, 1.6f, 1.2f, new Vec3(1.3, 1, 1.3));
        var tail = new DinoPart<>(this, "tail", 0.8f, 0.8F, 0.8f, new Vec3(-1.3, 1.3, -1.3));
        var tailEnd = new DinoPart<>(this, "tail", 0.8F, 0.8F, 0.5f, new Vec3(-2.2, 1.3, -2.2));
        this.subEntities = List.of(head, chest, tail, tailEnd);
        this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.size() + 1) + 1);
    }

    public static AttributeSupplier.Builder attributes() {
        return AgeableMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 55)
                .add(Attributes.ATTACK_DAMAGE, 15)
                //todo
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 20);
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
        return EntityRegistry.CERATOSAURUS.get().create(pLevel);
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
    public DinoPart<Ceratosaurus> getHead() {
        return head;
    }

    @Override
    float sleepingOffset() {
        // TODO: Set the correct offset.
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
        // todo dino meat
        return false;
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return AABB.ofSize(position(), 8, 8, 8);
    }

    @Override
    protected AABB getAttackBoundingBox() {
        return super.getAttackBoundingBox();
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
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
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, true, entity -> {
            if (entity instanceof Ceratosaurus || !entity.isAttackable()) {
                return false;
            }
            if (entity instanceof Player player) {
                return !player.isCreative();
            }
            return true;
        }));
    }


    @Override
    public void tick() {
        super.tick();
//        if (getTarget() != null) {
//            if (!getEntityData().get(RUNNING)) {
//                getEntityData().set(RUNNING, true);
//            }
//        } else {
//            if (getEntityData().get(RUNNING)) {
//                getEntityData().set(RUNNING, false);
//            }
//        }
//        if (level().isClientSide) {
//            animateWhen("idle", !isMoving(this) && !isSleeping());
//            animateWhen("sleep", isSleeping());
//            animateWhen("sit", isInSittingPose());
//        }
        if (level().isClientSide()) {
            animateWhen("walk", true);
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
        return Optional.ofNullable(getAnimations().get().get(animation));
    }

    @Override
    public int tickCount() {
        return tickCount;
    }

    @Override
    public Variant getVariant() {
        return entityData.get(VARIANT);
    }

    @Override
    public void setVariant(Variant pVariant) {
        entityData.set(VARIANT, pVariant);
    }

    @Override
    public SleepingPattern getSleepingPattern() {
        return SleepingPattern.CATHEMERAL;
    }


    public List<DinoPart<Ceratosaurus>> getSubEntities() {
        return subEntities;
    }

    @Override
    public void setSleeping(boolean sleeping) {
        /* todo change the hitbox if needed
        if (isSleeping()) {
            if (!sleeping) {
                dimensions = dimensions.scale(1f, 2f);
                level().broadcastEntityEvent(this, (byte) 0);
            }
        } else if (sleeping) {
            dimensions = dimensions.scale(1f, 0.5f);
            level().broadcastEntityEvent(this, (byte) 1);
        }
        super.setSleeping(sleeping);*/
    }

    @Override
    public void swing(InteractionHand hand) {
        /* todo implement attack animation
        super.swing(hand);
        if (level().isClientSide()) {
            replayAnimation("attack");
        }*/
    }

    @Override
    public void handleEntityEvent(byte id) {
        /* todo implement any sort of animations.
        super.handleEntityEvent(id);
        if (id == 0) {
            dimensions = dimensions.scale(1f, 2f);
        } else if (id == 1) {
            dimensions = dimensions.scale(1f, 0.5f);
        } else if (id == 2) {
            replayAnimation("roar");
        }*/
    }

    public enum Variant {
        MALE(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "textures/entity/ceratosaurus_male.png")),
        FEMALE(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "textures/entity/ceratosaurus_female.png"));

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
