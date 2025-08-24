package io.github.itskillerluc.alternacraft.effects;

import io.github.itskillerluc.alternacraft.init.AttachmentTypeRegistry;
import io.github.itskillerluc.alternacraft.networking.SetStunnedPayload;
import net.minecraft.client.Camera;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public class StunEffect extends MobEffect {
    public StunEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration != 0;
    }

    @Override
    public void onEffectStarted(@NotNull LivingEntity livingEntity, int amplifier) {
        freeze(livingEntity);
    }

    private void freeze(LivingEntity livingEntity) {
        if (livingEntity instanceof Mob mob) {
            mob.yHeadRot = mob.yBodyRot;
            mob.setNoAi(true);
        }
        if (!livingEntity.getData(AttachmentTypeRegistry.STUNNED)) {
            livingEntity.setData(AttachmentTypeRegistry.STUNNED, true);
            if (!livingEntity.level().isClientSide) {
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(livingEntity, new SetStunnedPayload(livingEntity.getId(), true));
            }
        }
    }

    public static void unFreeze(LivingEntity livingEntity) {
        if (livingEntity instanceof Mob mob) {
            mob.setNoAi(false);
        }
        if (livingEntity.getData(AttachmentTypeRegistry.STUNNED)) {
            if (livingEntity.level().isClientSide()) {
                livingEntity.setData(AttachmentTypeRegistry.STUNNED, false);
            } else {
                livingEntity.setData(AttachmentTypeRegistry.STUNNED, false);
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(livingEntity, new SetStunnedPayload(livingEntity.getId(), false));
            }
        }
    }

    public static void screenShake(float intensity, Camera camera, long gameTime, double partialTick) {

    }
}
