package io.github.itskillerluc.entity.ai;

import io.github.itskillerluc.entity.DinoEntity;
import io.github.itskillerluc.entity.DinoPart;
import io.github.itskillerluc.entity.Magmatyrannus;
import io.github.itskillerluc.networking.ParticlePayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.EnumSet;

public class BreathAttackGoal extends Goal {
    private final DinoEntity<?> dino;
    @Nullable
    private LivingEntity target;
    private int attackTime = -1;
    private int attackingTime = 0;
    private int animationTimer = 0;
    private final double speedModifier;
    private int seeTime;
    private final int damage;
    private final ParticleOptions particle;
    private final int maxAttackingTime;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;
    private final float attackRadiusSqr;
    private final float particleSpeed;
    private final float maxFireAngle;
    private final int spread;
    private final int particleCount;
    private final int animationDelay;
    private final Vec3 offset;
    private final float distance;

    public BreathAttackGoal(DinoEntity<?> dinoEntity, double speedModifier, int damage, ParticleOptions particle, int maxAttackingTime, int attackIntervalMin, int attackIntervalMax, float attackRadius, float particleSpeed, int spread, int particleCount, int animationDelay, Vec3 offset, float distance, float maxFireAngle) {
        this.dino = dinoEntity;
        this.speedModifier = speedModifier;
        this.damage = damage;
        this.particle = particle;
        this.maxAttackingTime = maxAttackingTime;
        this.attackIntervalMin = attackIntervalMin;
        this.attackIntervalMax = attackIntervalMax;
        this.attackRadius = attackRadius;
        this.attackRadiusSqr = attackRadius * attackRadius;
        this.particleSpeed = particleSpeed;
        this.spread = spread;
        this.particleCount = particleCount;
        this.animationDelay = animationDelay;
        this.offset = offset;
        this.distance = distance;
        this.maxFireAngle = maxFireAngle;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingEntity = dino.getTarget();
        if (livingEntity != null && livingEntity.isAlive()) {
            this.target = livingEntity;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || this.target.isAlive() && !this.dino.getNavigation().isDone();
    }

    @Override
    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.attackTime = -1;
    }

    @Override
    public void start() {
        super.start();
        animationTimer = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        double d0 = this.dino.distanceToSqr(this.target);
        boolean flag = this.dino.getSensing().hasLineOfSight(this.target);
        if (flag) {
            this.seeTime++;
        } else {
            this.seeTime = 0;
        }

        if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 5) {
            this.dino.getNavigation().stop();
            var rotation = dino.getLookAngle().toVector3f().angle(target.position().toVector3f());
            if (!(rotation < maxFireAngle || 2 * Math.PI - rotation < maxFireAngle)) {
                flag = false;
                this.dino.getLookControl().setLookAt(target);
            }
        } else {
            this.dino.getNavigation().moveTo(this.target, this.speedModifier);
        }

        if (--this.attackTime == 0 || attackingTime++ <= maxAttackingTime) {
            if (!flag) {
                attackTime++;
                return;
            }

            dino.getEntityData().set(Magmatyrannus.BREATHING_FIRE, true);

            float f = (float)Math.sqrt(d0) / this.attackRadius;

            if (animationTimer++ >= animationDelay) {
                attack();
            }

            if (this.attackTime == 0) {
                attackingTime = 0;
            }
            if (attackingTime == maxAttackingTime) {
                this.attackTime = Mth.floor(f * (float) (this.attackIntervalMax - this.attackIntervalMin) + (float) this.attackIntervalMin);
                animationTimer = 0;
            }
        } else {
            dino.getEntityData().set(Magmatyrannus.BREATHING_FIRE, false);
        }
    }

    private void attack() {
        DinoPart<?> origin = dino.getHead();
        var rotationMatrix = new Matrix3f().rotationTowards(Vec3.directionFromRotation(dino.getRotationVector()).toVector3f(), new Vector3f(0, 1, 0));
        var originVec = origin.position().add(new Vec3(rotationMatrix.transform(offset.toVector3f())));
        Vector3f targetVector = originVec.vectorTo(target.position()).toVector3f();

        PacketDistributor.sendToPlayersTrackingEntity(this.dino, new ParticlePayload(targetVector.mul(particleSpeed), particle, originVec.toVector3f(), spread, particleCount));
    }
}
