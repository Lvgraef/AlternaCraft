package io.github.itskillerluc.alternacraft.init;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ToolTiers {
    public static final Tier AIO_TIER = new SimpleTier(Tags.Blocks.INCORRECT_AIO, 4269, 15, 7, 25,() -> Ingredient.of(ItemRegistry.AIO_CRYSTAL.get()));
    public static final Tier COPPERWOOD_TIER = new SimpleTier(Tags.Blocks.INCORRECT_DARK_CRYSTAL, 2407, 10, 5, 15,() -> Ingredient.of(ItemRegistry.COPPERWOOD_CRYSTAL.get()));
    public static final Tier MAGNETIC_TIER = new SimpleTier(Tags.Blocks.INCORRECT_DARK_CRYSTAL, 2809, 12, 5, 15,() -> Ingredient.of(ItemRegistry.MAGNETIC_CRYSTAL.get()));
    public static final Tier PAINITE_TIER = new SimpleTier(Tags.Blocks.INCORRECT_DARK_CRYSTAL, 3100, 9, 5, 20,() -> Ingredient.of(ItemRegistry.PAINITE_CRYSTAL.get()));
    public static final Tier DARK_CRYSTAL_TIER = new SimpleTier(Tags.Blocks.INCORRECT_DARK_CRYSTAL, 2600, 10, 6, 15,() -> Ingredient.of(ItemRegistry.DARK_CRYSTAL.get()));
}
