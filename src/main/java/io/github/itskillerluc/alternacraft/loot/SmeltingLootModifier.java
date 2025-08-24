package io.github.itskillerluc.alternacraft.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SmeltingLootModifier extends LootModifier {
    public static final Supplier<MapCodec<SmeltingLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(
            inst -> codecStart(inst).apply(inst, SmeltingLootModifier::new)));

    public SmeltingLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
        ObjectArrayList<ItemStack> ret = new ObjectArrayList<>();
        generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
        return ret;
    }

    private static ItemStack smelt(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(stack), context.getLevel())
                .map(smeltingRecipe -> smeltingRecipe.value().getResultItem(context.getLevel().registryAccess()))
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> itemStack.copyWithCount(stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }

    @Override
    public @NotNull MapCodec<SmeltingLootModifier> codec() {
        return CODEC.get();
    }
}
