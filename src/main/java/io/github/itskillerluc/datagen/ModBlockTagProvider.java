package io.github.itskillerluc.datagen;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.BlockRegistry;
import io.github.itskillerluc.init.Tags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AlternaCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(BlockTags.DIRT)
                .add(BlockRegistry.VOLCANIC_SOIL.get())
                .add(BlockRegistry.MOSSY_DIRT.value())
                .add(BlockRegistry.MOSSY_GRASS.get());
        tag(BlockTags.FLOWERS)
                .add(BlockRegistry.BLUE_PASTEL_PETALS.value())
                .add(BlockRegistry.RED_PASTEL_PETALS.value());
        tag(BlockTags.LEAVES)
                .add(BlockRegistry.ELECTREE_LEAVES.value())
                .add(BlockRegistry.BLUE_PASTEL_LEAVES.value())
                .add(BlockRegistry.RED_PASTEL_LEAVES.value());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(BlockRegistry.CHARRED_BARK.value())
                .add(BlockRegistry.STRIPPED_CHARRED_BARK.value())
                .add(BlockRegistry.ELECTREE_LOG.value())
                .add(BlockRegistry.STRIPPED_ELECTREE_LOG.value())
                .add(BlockRegistry.BLUE_PASTEL_LOG.value())
                .add(BlockRegistry.RED_PASTEL_LOG.value())
                .add(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG.value())
                .add(BlockRegistry.STRIPPED_RED_PASTEL_LOG.value());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegistry.CHARRED_BARK.value())
                .add(BlockRegistry.CHARRED_PLANKS.value())
                .add(BlockRegistry.STRIPPED_CHARRED_BARK.value())
                .add(BlockRegistry.ELECTREE_LOG.value())
                .add(BlockRegistry.STRIPPED_ELECTREE_LOG.value());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.VOLCANIC_ROCK.get())
                .add(BlockRegistry.PAINITE_ORE.get())
                .add(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value())
                .add(BlockRegistry.DARK_CRYSTAL_ORE.value())
                .add(BlockRegistry.COPPERWOOD_ORE.value())
                .add(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.VOLCANIC_SOIL.get())
                .add(BlockRegistry.FROZEN_SAND.value());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockRegistry.PAINITE_ORE.get())
                .add(BlockRegistry.COPPERWOOD_ORE.value())
                .add(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value())
                .add(BlockRegistry.DARK_CRYSTAL_ORE.value())
                .add(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value());
        tag(BlockTags.PLANKS)
                .add(BlockRegistry.CHARRED_PLANKS.value())
                .add(BlockRegistry.ELECTREE_PLANKS.value())
                .add(BlockRegistry.BLUE_PASTEL_PLANKS.value())
                .add(BlockRegistry.RED_PASTEL_PLANKS.value());
        tag(BlockTags.SAND)
                .add(BlockRegistry.FROZEN_SAND.value());
        tag(BlockTags.SMALL_FLOWERS)
                .add(BlockRegistry.DEAD_DANDELION.value())
                .add(BlockRegistry.DEAD_POPPY.value())
                .add(BlockRegistry.BLUE_BULB.value())
                .add(BlockRegistry.GREEN_ROSE_BULB.value())
                .add(BlockRegistry.ORANGE_BULB.value())
                .add(BlockRegistry.BLUE_COLORFUL_FLOWER.value())
                .add(BlockRegistry.CYAN_COLORFUL_FLOWER.value())
                .add(BlockRegistry.GREEN_COLORFUL_FLOWER.value())
                .add(BlockRegistry.LIME_COLORFUL_FLOWER.value())
                .add(BlockRegistry.ORANGE_COLORFUL_FLOWER.value())
                .add(BlockRegistry.PINK_COLORFUL_FLOWER.value())
                .add(BlockRegistry.PURPLE_COLORFUL_FLOWER.value())
                .add(BlockRegistry.YELLOW_COLORFUL_FLOWER.value());
        tag(Tags.Blocks.BLUE_PASTEL_LOGS)
                .add(BlockRegistry.BLUE_PASTEL_LOG.value())
                .add(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG.value());
        tag(Tags.Blocks.CHARRED_LOGS)
                .add(BlockRegistry.CHARRED_BARK.value())
                .add(BlockRegistry.STRIPPED_CHARRED_BARK.value());
        tag(Tags.Blocks.COPPERWOOD_ORES)
                .add(BlockRegistry.COPPERWOOD_ORE.value())
                .add(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value());
        tag(Tags.Blocks.DARK_CRYSTAL_ORES)
                .add(BlockRegistry.DARK_CRYSTAL_ORE.value())
                .add(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value());
        tag(Tags.Blocks.ELECTREE_LOGS)
                .add(BlockRegistry.ELECTREE_LOG.value())
                .add(BlockRegistry.STRIPPED_ELECTREE_LOG.value());
        tag(Tags.Blocks.NEEDS_AIO_TOOL);
        tag(Tags.Blocks.NEEDS_DARK_CRYSTAL_TOOL);
        tag(Tags.Blocks.PAINITE_ORES)
                .add(BlockRegistry.PAINITE_ORE.value());
        tag(Tags.Blocks.RED_PASTEL_LOGS)
                .add(BlockRegistry.RED_PASTEL_LOG.value())
                .add(BlockRegistry.STRIPPED_RED_PASTEL_LOG.value());
        tag(Tags.Blocks.INCORRECT_AIO);
        tag(Tags.Blocks.INCORRECT_DARK_CRYSTAL)
                .addTag(Tags.Blocks.NEEDS_AIO_TOOL);
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(Tags.Blocks.NEEDS_AIO_TOOL)
                .addTag(Tags.Blocks.NEEDS_DARK_CRYSTAL_TOOL);
        tag(BlockTags.SAPLINGS)
                .add(BlockRegistry.ELECTREE_SAPLING.get())
                .add(BlockRegistry.BLUE_PASTELIZED_SAPLING.get())
                .add(BlockRegistry.RED_PASTELIZED_SAPLING.get());
    }
}
