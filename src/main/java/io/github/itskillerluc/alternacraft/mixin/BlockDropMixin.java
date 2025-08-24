package io.github.itskillerluc.alternacraft.mixin;

import io.github.itskillerluc.alternacraft.init.ToolTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Block.class)
@Debug(export = true)
@SuppressWarnings("unused")
public class BlockDropMixin {
    @Inject(at = @At("RETURN"), method = "Lnet/minecraft/world/level/block/Block;getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;", cancellable = true)
    private static void alternacraft$getDrops(BlockState pState, ServerLevel pLevel, BlockPos pPos, @Nullable BlockEntity pBlockEntity, @Nullable Entity pEntity, ItemStack pTool, CallbackInfoReturnable<List<ItemStack>> cbi) {
        if (pEntity instanceof Player player && player.getMainHandItem().getItem() instanceof DiggerItem diggerItem && (diggerItem.getTier() == ToolTiers.MAGNETIC_TIER || diggerItem.getTier() == ToolTiers.AIO_TIER)) {
            for (ItemStack itemStack : cbi.getReturnValue()) {
                if (!player.addItem(itemStack)) {
                    player.spawnAtLocation(itemStack);
                }
            }
            cbi.setReturnValue(List.of());
        }
    }
}
