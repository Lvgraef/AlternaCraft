package io.github.itskillerluc.item;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.DataComponentRegistry;
import io.github.itskillerluc.init.ItemAbilities;
import io.github.itskillerluc.init.SoundEventRegistry;
import io.github.itskillerluc.networking.SoundPayload;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DarkCrystalPickaxe extends PickaxeItem {
    public DarkCrystalPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.attributes(PickaxeItem.createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier)));
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility toolAction) {
        return super.canPerformAction(stack, toolAction) || toolAction.equals(ItemAbilities.SONAR);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        darkCrystalUse(pLevel, pPlayer, pUsedHand);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    public static InteractionResultHolder<ItemStack> darkCrystalUse(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) return InteractionResultHolder.pass(ItemStack.EMPTY);
        int range = 15;
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!stack.has(DataComponentRegistry.DISTANCE.get())) {
            BlockPos closestBlock = null;

            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    for (int z = -range; z <= range; z++) {
                        var offsetPos = pPlayer.getOnPos().offset(x, y, z);
                        if (pLevel.getBlockState(offsetPos).getBlock().asItem() == pPlayer.getItemInHand(pUsedHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND).getItem()) {
                            if (closestBlock == null || offsetPos.distSqr(pPlayer.getOnPos()) < closestBlock.distSqr(pPlayer.getOnPos())) {
                                closestBlock = offsetPos;
                            }
                        }
                    }
                }
            }
            if (closestBlock != null) {
                stack.set(DataComponentRegistry.GAMETIME.get(), pLevel.getGameTime());
                stack.set(DataComponentRegistry.DISTANCE.get(), Math.sqrt(closestBlock.distSqr(pPlayer.getOnPos())));
            }
            PacketDistributor.sendToPlayer((ServerPlayer) pPlayer, new SoundPayload(SoundEventRegistry.SONAR_PING.get().getLocation().toString()));
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        darkCrystalTick(pStack, pLevel, pEntity);
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @SuppressWarnings("DataFlowIssue")
    public void darkCrystalTick(ItemStack pStack, Level pLevel, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof ServerPlayer player) {
            if (pStack.has(DataComponentRegistry.GAMETIME.get()) && pStack.has(DataComponentRegistry.DISTANCE.get())) {
                if (pLevel.getGameTime() - pStack.get(DataComponentRegistry.GAMETIME.get()) > 5L * pStack.get(DataComponentRegistry.DISTANCE.get())) {
                    pStack.remove(DataComponentRegistry.GAMETIME.get());
                    pStack.remove(DataComponentRegistry.DISTANCE.get());
                    PacketDistributor.sendToPlayer(player, new SoundPayload(SoundEventRegistry.SONAR_PONG.get().getLocation().toString()));
                }
            }
        }
        }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("description." + AlternaCraft.MODID + "." + "dark_crystal_pickaxe").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
