package io.github.itskillerluc.effects;

import io.github.itskillerluc.init.AttachmentTypeRegistry;
import io.github.itskillerluc.networking.SetStunnedPayload;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
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
            if (!livingEntity.level().isClientSide && !(livingEntity instanceof Player)) {
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(livingEntity, new SetStunnedPayload(livingEntity.getId(), true));
            }
        }
    }

    private void unFreeze(LivingEntity livingEntity) {
        if (livingEntity instanceof Mob mob) {
            mob.setNoAi(false);
        }
        if (livingEntity.getData(AttachmentTypeRegistry.STUNNED)) {
            if (livingEntity.level().isClientSide()) {
                livingEntity.setData(AttachmentTypeRegistry.STUNNED, false);
            } else {
                livingEntity.setData(AttachmentTypeRegistry.STUNNED, false);
                if (!(livingEntity instanceof Player)) {
                    PacketDistributor.sendToPlayersTrackingEntityAndSelf(livingEntity, new SetStunnedPayload(livingEntity.getId(), false));
                }
            }
        }
    }
}
