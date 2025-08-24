package io.github.itskillerluc.alternacraft.datagen;

import io.github.itskillerluc.alternacraft.init.ItemRegistry;
import io.github.itskillerluc.alternacraft.init.Tags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {
        armor(ArmorItem.Type.BOOTS, ItemRegistry.COPPERWOOD_BOOTS.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.BOOTS, ItemRegistry.DARK_CRYSTAL_BOOTS.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.BOOTS, ItemRegistry.MAGNETIC_BOOTS.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.BOOTS, ItemRegistry.PAINITE_BOOTS.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.CHESTPLATE, ItemRegistry.COPPERWOOD_CHESTPLATE.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.CHESTPLATE, ItemRegistry.DARK_CRYSTAL_CHESTPLATE.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.CHESTPLATE, ItemRegistry.MAGNETIC_CHESTPLATE.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.CHESTPLATE, ItemRegistry.PAINITE_CHESTPLATE.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.HELMET, ItemRegistry.COPPERWOOD_HELMET.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.HELMET, ItemRegistry.DARK_CRYSTAL_HELMET.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.HELMET, ItemRegistry.MAGNETIC_HELMET.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.HELMET, ItemRegistry.PAINITE_HELMET.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.LEGGINGS, ItemRegistry.COPPERWOOD_LEGGINGS.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.LEGGINGS, ItemRegistry.DARK_CRYSTAL_LEGGINGS.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.LEGGINGS, ItemRegistry.MAGNETIC_LEGGINGS.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        armor(ArmorItem.Type.LEGGINGS, ItemRegistry.PAINITE_LEGGINGS.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.AIO_HELMET.get())
                .pattern("iii")
                .pattern("isi")
                .define('i', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.AIO_CHESTPLATE.get())
                .pattern("i i")
                .pattern("isi")
                .pattern("iii")
                .define('i', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.AIO_LEGGINGS.get())
                .pattern("iii")
                .pattern("isi")
                .pattern("i i")
                .define('i', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.AIO_BOOTS.get())
                .pattern("isi")
                .pattern("i i")
                .define('i', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get()))
                .save(pRecipeOutput);
        axe(ItemRegistry.COPPERWOOD_AXE.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        axe(ItemRegistry.DARK_CRYSTAL_AXE.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        axe(ItemRegistry.MAGNETIC_AXE.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        axe(ItemRegistry.PAINITE_AXE.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        hoe(ItemRegistry.COPPERWOOD_HOE.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        hoe(ItemRegistry.DARK_CRYSTAL_HOE.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        hoe(ItemRegistry.MAGNETIC_HOE.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        hoe(ItemRegistry.PAINITE_HOE.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        pickaxe(ItemRegistry.COPPERWOOD_PICKAXE.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        pickaxe(ItemRegistry.DARK_CRYSTAL_PICKAXE.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        pickaxe(ItemRegistry.MAGNETIC_PICKAXE.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        pickaxe(ItemRegistry.PAINITE_PICKAXE.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        shovel(ItemRegistry.COPPERWOOD_SHOVEL.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        shovel(ItemRegistry.DARK_CRYSTAL_SHOVEL.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        shovel(ItemRegistry.MAGNETIC_SHOVEL.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        shovel(ItemRegistry.PAINITE_SHOVEL.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        sword(ItemRegistry.COPPERWOOD_SWORD.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), pRecipeOutput);
        sword(ItemRegistry.DARK_CRYSTAL_SWORD.get(), ItemRegistry.DARK_CRYSTAL.get(), pRecipeOutput);
        sword(ItemRegistry.MAGNETIC_SWORD.get(), ItemRegistry.MAGNETIC_CRYSTAL.get(), pRecipeOutput);
        sword(ItemRegistry.PAINITE_SWORD.get(), ItemRegistry.PAINITE_CRYSTAL.get(), pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.AIO_SWORD.get())
                .pattern(" a ")
                .pattern(" a ")
                .pattern("sps")
                .define('a', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .define('p', ItemRegistry.PRISMA_STICK.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get(), ItemRegistry.PRISMA_STICK.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.AIO_HOE.get())
                .pattern("aa ")
                .pattern(" p ")
                .pattern("sps")
                .define('a', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .define('p', ItemRegistry.PRISMA_STICK.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get(), ItemRegistry.PRISMA_STICK.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.AIO_AXE.get())
                .pattern("aa ")
                .pattern("ap ")
                .pattern("sps")
                .define('a', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .define('p', ItemRegistry.PRISMA_STICK.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get(), ItemRegistry.PRISMA_STICK.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.AIO_PICKAXE.get())
                .pattern("aaa")
                .pattern(" p ")
                .pattern("sps")
                .define('a', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .define('p', ItemRegistry.PRISMA_STICK.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get(), ItemRegistry.PRISMA_STICK.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.AIO_SHOVEL.get())
                .pattern(" a ")
                .pattern(" p ")
                .pattern("sps")
                .define('a', ItemRegistry.AIO_CRYSTAL.get())
                .define('s', ItemRegistry.MINI_STAR.get())
                .define('p', ItemRegistry.PRISMA_STICK.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.AIO_CRYSTAL.get(), ItemRegistry.MINI_STAR.get(), ItemRegistry.PRISMA_STICK.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.PRISMA_STICK.get())
                .pattern("p")
                .pattern("p")
                .define('p', ItemRegistry.PRISAMOND_SHARD.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.PRISAMOND_SHARD.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.MINI_STAR.get())
                .pattern("mgp")
                .pattern("gng")
                .pattern("cgd")
                .define('m', ItemRegistry.MAGNETIC_CRYSTAL.get())
                .define('g', Items.GOLD_BLOCK)
                .define('p', ItemRegistry.PAINITE_CRYSTAL.get())
                .define('c', ItemRegistry.COPPERWOOD_CRYSTAL.get())
                .define('d', ItemRegistry.DARK_CRYSTAL.get())
                .define('n', Items.NETHER_STAR)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.MAGNETIC_CRYSTAL.get(), ItemRegistry.PAINITE_CRYSTAL.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), ItemRegistry.DARK_CRYSTAL.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.AIO_CRYSTAL.get())
                .requires(ItemRegistry.MAGNETIC_CRYSTAL.get())
                .requires(ItemRegistry.PAINITE_CRYSTAL.get())
                .requires(ItemRegistry.COPPERWOOD_CRYSTAL.get())
                .requires(ItemRegistry.DARK_CRYSTAL.get())
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.MAGNETIC_CRYSTAL.get(), ItemRegistry.PAINITE_CRYSTAL.get(), ItemRegistry.COPPERWOOD_CRYSTAL.get(), ItemRegistry.DARK_CRYSTAL.get()))
                .save(pRecipeOutput);
        oneToOneConversionRecipe(pRecipeOutput, Items.BLUE_DYE, ItemRegistry.BLUE_BULB.get(), "blue_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.BLUE_DYE, ItemRegistry.BLUE_COLORFUL_FLOWER.get(), "blue_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.CYAN_DYE, ItemRegistry.CYAN_COLORFUL_FLOWER.get(), "cyan_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.GREEN_DYE, ItemRegistry.GREEN_COLORFUL_FLOWER.get(), "green_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.LIME_DYE, ItemRegistry.GREEN_ROSE_BULB.get(), "lime_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.LIME_DYE, ItemRegistry.LIME_COLORFUL_FLOWER.get(), "lime_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.MAGENTA_DYE, ItemRegistry.MAGENTA_COLORFUL_FLOWER.get(), "magenta_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.ORANGE_DYE, ItemRegistry.ORANGE_BULB.get(), "orange_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.ORANGE_DYE, ItemRegistry.ORANGE_COLORFUL_FLOWER.get(), "orange_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.PINK_DYE, ItemRegistry.PINK_COLORFUL_FLOWER.get(), "pink_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.PURPLE_DYE, ItemRegistry.PURPLE_COLORFUL_FLOWER.get(), "purple_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.RED_DYE, ItemRegistry.DEAD_POPPY.get(), "red_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.YELLOW_DYE, ItemRegistry.DEAD_POPPY.get(), "yellow_dye");
        oneToOneConversionRecipe(pRecipeOutput, Items.YELLOW_DYE, ItemRegistry.YELLOW_COLORFUL_FLOWER.get(), "yellow_dye");
        oreBlasting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_COPPERWOOD_ORE.get(), ItemRegistry.COPPERWOOD_ORE.get()), RecipeCategory.MISC, ItemRegistry.COPPERWOOD_CRYSTAL.get(), 0.7f, 100, "copperwood_crystal");
        oreBlasting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_MAGNETIC_ORE.get(), ItemRegistry.MAGNETIC_ORE.get()), RecipeCategory.MISC, ItemRegistry.MAGNETIC_CRYSTAL.get(), 0.7f, 100, "magnetic_crystal");
        oreBlasting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.get(), ItemRegistry.DARK_CRYSTAL_ORE.get()), RecipeCategory.MISC, ItemRegistry.DARK_CRYSTAL.get(), 0.7f, 100, "dark_crystal");
        oreBlasting(pRecipeOutput, List.of(ItemRegistry.PAINITE_ORE_SOIL.get()), RecipeCategory.MISC, ItemRegistry.PAINITE_CRYSTAL.get(), 0.7f, 100, "painite_crystal");
        oreSmelting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_COPPERWOOD_ORE.get(), ItemRegistry.COPPERWOOD_ORE.get()), RecipeCategory.MISC, ItemRegistry.COPPERWOOD_CRYSTAL.get(), 0.7f, 200, "copperwood_crystal");
        oreSmelting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_MAGNETIC_ORE.get(), ItemRegistry.MAGNETIC_ORE.get()), RecipeCategory.MISC, ItemRegistry.MAGNETIC_CRYSTAL.get(), 0.7f, 200, "magnetic_crystal");
        oreSmelting(pRecipeOutput, List.of(ItemRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.get(), ItemRegistry.DARK_CRYSTAL_ORE.get()), RecipeCategory.MISC, ItemRegistry.DARK_CRYSTAL.get(), 0.7f, 200, "dark_crystal");
        oreSmelting(pRecipeOutput, List.of(ItemRegistry.PAINITE_ORE_SOIL.get()), RecipeCategory.MISC, ItemRegistry.PAINITE_CRYSTAL.get(), 0.7f, 200, "painite_crystal");
        planksFromLog(pRecipeOutput, ItemRegistry.BLUE_PASTEL_PLANKS.get(), Tags.Items.BLUE_PASTEL_LOGS, 4);
        planksFromLog(pRecipeOutput, ItemRegistry.CHARRED_PLANKS.get(), Tags.Items.CHARRED_LOGS, 4);
        planksFromLog(pRecipeOutput, ItemRegistry.ELECTREE_PLANKS.get(), Tags.Items.ELECTREE_LOGS, 4);
        planksFromLog(pRecipeOutput, ItemRegistry.RED_PASTEL_PLANKS.get(), Tags.Items.RED_PASTEL_LOGS, 4);
    }

    private static void pickaxe(ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', material)
                .define('s', Items.STICK)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput);
    }

    private static void shovel(ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("i")
                .pattern("s")
                .pattern("s")
                .define('i', material)
                .define('s', Items.STICK)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput);
    }

    private static void axe(ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("ii")
                .pattern("is")
                .pattern(" s")
                .define('i', material)
                .define('s', Items.STICK)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput);
    }

    private static void hoe(ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("ii")
                .pattern(" s")
                .pattern(" s")
                .define('i', material)
                .define('s', Items.STICK)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput);
    }

    private static void sword(ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("i")
                .pattern("i")
                .pattern("s")
                .define('i', material)
                .define('s', Items.STICK)
                .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                .save(recipeOutput);
    }

    private static void armor(ArmorItem.Type type, ItemLike result, ItemLike material, RecipeOutput recipeOutput) {
        switch (type) {
            case HELMET -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                    .pattern("iii")
                    .pattern("i i")
                    .define('i', material)
                    .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                    .save(recipeOutput);
            case CHESTPLATE -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                    .pattern("i i")
                    .pattern("iii")
                    .pattern("iii")
                    .define('i', material)
                    .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                    .save(recipeOutput);
            case LEGGINGS -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                    .pattern("iii")
                    .pattern("i i")
                    .pattern("i i")
                    .define('i', material)
                    .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                    .save(recipeOutput);
            case BOOTS -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                    .pattern("i i")
                    .pattern("i i")
                    .define('i', material)
                    .unlockedBy("criteria", InventoryChangeTrigger.TriggerInstance.hasItems(material))
                    .save(recipeOutput);
        }

    }
}
