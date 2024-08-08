package io.github.itskillerluc.datagen;

import io.github.itskillerluc.init.BlockRegistry;
import io.github.itskillerluc.init.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream()
                .map(holder -> ((Block) holder.get()))::iterator;
    }

    @Override
    protected void generate() {
        add(BlockRegistry.BLUE_PASTEL_LEAVES.get(), createLeavesDrops(BlockRegistry.BLUE_PASTEL_LEAVES.get(), Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        add(BlockRegistry.BLUE_PASTEL_PETALS.get(), createPetalsDrops(BlockRegistry.BLUE_PASTEL_PETALS.get()));
        add(BlockRegistry.COPPERWOOD_ORE.value(), createOreDrop(BlockRegistry.COPPERWOOD_ORE.value(), ItemRegistry.COPPERWOOD_CRYSTAL.get()));
        add(BlockRegistry.DARK_CRYSTAL_ORE.value(), createOreDrop(BlockRegistry.DARK_CRYSTAL_ORE.value(), ItemRegistry.DARK_CRYSTAL.get()));
        add(BlockRegistry.DEAD_GRASS.get(), createGrassDrops(BlockRegistry.DEAD_GRASS.get()));
        add(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value(), createOreDrop(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value(), ItemRegistry.COPPERWOOD_CRYSTAL.get()));
        add(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value(), createOreDrop(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value(), ItemRegistry.DARK_CRYSTAL.get()));
        add(BlockRegistry.ELECTREE_LEAVES.get(), createLeavesDrops(BlockRegistry.ELECTREE_LEAVES.value(), Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        add(BlockRegistry.MINING_LIGHT.get(), noDrop());
        add(BlockRegistry.MOSSY_GRASS.value(), createSingleItemTableWithSilkTouch(BlockRegistry.MOSSY_GRASS.value(), ItemRegistry.MOSSY_GRASS.get()));
        add(BlockRegistry.PAINITE_ORE_SOIL.get(),createOreDrop(BlockRegistry.PAINITE_ORE_SOIL.get(), ItemRegistry.PAINITE_CRYSTAL.get()));
        add(BlockRegistry.RED_PASTEL_LEAVES.get(), createLeavesDrops(BlockRegistry.RED_PASTEL_LEAVES.get(), Blocks.OAK_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        add(BlockRegistry.RED_PASTEL_PETALS.get(), createPetalsDrops(BlockRegistry.RED_PASTEL_PETALS.get()));
        dropSelf(BlockRegistry.BLUE_BULB.get());
        dropSelf(BlockRegistry.BLUE_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.BLUE_PASTEL_LOG.get());
        dropSelf(BlockRegistry.BLUE_PASTEL_PLANKS.value());
        dropSelf(BlockRegistry.ELECTREE_SAPLING.value());
        dropSelf(BlockRegistry.CHARRED_BARK.get());
        dropSelf(BlockRegistry.CHARRED_PLANKS.value());
        dropSelf(BlockRegistry.CYAN_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.DEAD_DANDELION.get());
        dropSelf(BlockRegistry.DEAD_POPPY.get());
        dropSelf(BlockRegistry.ELECTREE_LOG.value());
        dropSelf(BlockRegistry.ELECTREE_PLANKS.value());
        dropSelf(BlockRegistry.FROZEN_CACTUS.value());
        dropSelf(BlockRegistry.FROZEN_SAND.value());
        dropSelf(BlockRegistry.GREEN_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.GREEN_ROSE_BULB.get());
        dropSelf(BlockRegistry.LIME_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.MAGENTA_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.MOSSY_DIRT.value());
        dropSelf(BlockRegistry.ORANGE_BULB.get());
        dropSelf(BlockRegistry.ORANGE_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.PINK_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.PURPLE_COLORFUL_FLOWER.get());
        dropSelf(BlockRegistry.RED_PASTEL_LOG.get());
        dropSelf(BlockRegistry.RED_PASTEL_PLANKS.value());
        dropSelf(BlockRegistry.ROCKS.value());
        dropSelf(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG.get());
        dropSelf(BlockRegistry.STRIPPED_CHARRED_BARK.get());
        dropSelf(BlockRegistry.STRIPPED_ELECTREE_LOG.value());
        dropSelf(BlockRegistry.STRIPPED_RED_PASTEL_LOG.get());
        dropSelf(BlockRegistry.VOLCANIC_ROCK.get());
        dropSelf(BlockRegistry.VOLCANIC_SOIL.get());
        dropSelf(BlockRegistry.YELLOW_COLORFUL_FLOWER.get());
    }
}
