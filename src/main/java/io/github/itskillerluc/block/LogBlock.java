package io.github.itskillerluc.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LogBlock extends RotatedPillarBlock {
    private final Supplier<RotatedPillarBlock> stripped;
    public LogBlock(Properties props, Supplier<RotatedPillarBlock> stripped) {
        super(props);
        this.stripped = stripped;
    }
    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, @NotNull UseOnContext context, @NotNull ItemAbility toolAction, boolean simulate) {
        return toolAction == ItemAbilities.AXE_STRIP ? stripped.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)) : null;
    }
}
