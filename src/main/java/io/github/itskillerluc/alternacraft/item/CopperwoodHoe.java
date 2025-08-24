package io.github.itskillerluc.alternacraft.item;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.init.ItemAbilities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CopperwoodHoe extends HoeItem {
    public CopperwoodHoe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.attributes(HoeItem.createAttributes(pTier, pAttackDamageModifier, pAttackSpeedModifier)));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext pContext) {
        InteractionResult result = copperwoodUse(pContext);
        if (result == InteractionResult.PASS) {
            return super.useOn(pContext);
        } else {
            return result;
        }
    }

    public static InteractionResult copperwoodUse(UseOnContext pContext) {
        BlockState state = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (state.getBlock() instanceof CropBlock crop) {
            if (crop.isMaxAge(state)) {
                pContext.getLevel().destroyBlock(pContext.getClickedPos(), true, pContext.getPlayer());
                if (pContext.getPlayer() != null) {
                    int itemSlot = pContext.getPlayer().getInventory().findSlotMatchingItem(new ItemStack(crop.asItem()));
                    if (itemSlot > -1) {
                        pContext.getPlayer().getInventory().removeItem(itemSlot, 1);
                        pContext.getLevel().setBlock(pContext.getClickedPos(), crop.defaultBlockState(), 3);
                    }
                }
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility toolAction) {
        return super.canPerformAction(stack, toolAction) || toolAction.equals(ItemAbilities.CROP_HARVEST);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("description." + AlternaCraft.MODID + "." + "copperwood_hoe").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
