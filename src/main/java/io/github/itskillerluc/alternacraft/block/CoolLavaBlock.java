package io.github.itskillerluc.alternacraft.block;

import io.github.itskillerluc.alternacraft.init.FluidRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class CoolLavaBlock extends LiquidBlock {
    public CoolLavaBlock(Properties props) {
        super((FlowingFluid) FluidRegistry.COOL_LAVA.get(), props);
    }

    @Override
    public void entityInside(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, Entity pEntity) {
        if (!pEntity.fireImmune()) {
            pEntity.setRemainingFireTicks(300);
            if (pEntity.hurt(pEntity.damageSources().lava(), 8.0F)) {
                pEntity.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + pLevel.random.nextFloat() * 0.4F);
            }
        }
    }
}
