package io.github.itskillerluc.datagen;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.ItemRegistry;
import io.github.itskillerluc.init.Tags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, blockTags, AlternaCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        copy(BlockTags.DIRT, ItemTags.DIRT);
        copy(BlockTags.FLOWERS, ItemTags.FLOWERS) ;
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        copy(Tags.Blocks.BLUE_PASTEL_LOGS, Tags.Items.BLUE_PASTEL_LOGS);
        copy(Tags.Blocks.CHARRED_LOGS, Tags.Items.CHARRED_LOGS);
        copy(Tags.Blocks.COPPERWOOD_ORES, Tags.Items.COPPERWOOD_ORES);
        copy(Tags.Blocks.DARK_CRYSTAL_ORES, Tags.Items.DARK_CRYSTAL_ORES);
        copy(Tags.Blocks.ELECTREE_LOGS, Tags.Items.ELECTREE_LOGS);
        copy(Tags.Blocks.PAINITE_ORES, Tags.Items.PAINITE_ORES);
        tag(ItemTags.AXES)
                .add(ItemRegistry.AIO_AXE.value())
                .add(ItemRegistry.PAINITE_AXE.value())
                .add(ItemRegistry.DARK_CRYSTAL_AXE.value())
                .add(ItemRegistry.COPPERWOOD_AXE.value())
                .add(ItemRegistry.MAGNETIC_AXE.value());
        tag(ItemTags.HOES)
                .add(ItemRegistry.AIO_HOE.value())
                .add(ItemRegistry.PAINITE_HOE.value())
                .add(ItemRegistry.DARK_CRYSTAL_HOE.value())
                .add(ItemRegistry.COPPERWOOD_HOE.value())
                .add(ItemRegistry.MAGNETIC_HOE.value());
        tag(ItemTags.PICKAXES)
                .add(ItemRegistry.AIO_PICKAXE.value())
                .add(ItemRegistry.PAINITE_PICKAXE.value())
                .add(ItemRegistry.DARK_CRYSTAL_PICKAXE.value())
                .add(ItemRegistry.COPPERWOOD_PICKAXE.value())
                .add(ItemRegistry.MAGNETIC_PICKAXE.value());
        tag(ItemTags.SHOVELS)
                .add(ItemRegistry.AIO_SHOVEL.value())
                .add(ItemRegistry.PAINITE_SHOVEL.value())
                .add(ItemRegistry.DARK_CRYSTAL_SHOVEL.value())
                .add(ItemRegistry.COPPERWOOD_SHOVEL.value())
                .add(ItemRegistry.MAGNETIC_SHOVEL.value());
        tag(ItemTags.SWORDS)
                .add(ItemRegistry.AIO_SWORD.value())
                .add(ItemRegistry.PAINITE_SWORD.value())
                .add(ItemRegistry.DARK_CRYSTAL_SWORD.value())
                .add(ItemRegistry.COPPERWOOD_SWORD.value())
                .add(ItemRegistry.MAGNETIC_SWORD.value());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemRegistry.AIO_HELMET.value())
                .add(ItemRegistry.PAINITE_HELMET.value())
                .add(ItemRegistry.DARK_CRYSTAL_HELMET.value())
                .add(ItemRegistry.COPPERWOOD_HELMET.value())
                .add(ItemRegistry.MAGNETIC_HELMET.value())
                .add(ItemRegistry.AIO_CHESTPLATE.value())
                .add(ItemRegistry.PAINITE_CHESTPLATE.value())
                .add(ItemRegistry.DARK_CRYSTAL_CHESTPLATE.value())
                .add(ItemRegistry.COPPERWOOD_CHESTPLATE.value())
                .add(ItemRegistry.MAGNETIC_CHESTPLATE.value())
                .add(ItemRegistry.AIO_LEGGINGS.value())
                .add(ItemRegistry.PAINITE_LEGGINGS.value())
                .add(ItemRegistry.DARK_CRYSTAL_LEGGINGS.value())
                .add(ItemRegistry.COPPERWOOD_LEGGINGS.value())
                .add(ItemRegistry.MAGNETIC_LEGGINGS.value())
                .add(ItemRegistry.AIO_BOOTS.value())
                .add(ItemRegistry.PAINITE_BOOTS.value())
                .add(ItemRegistry.DARK_CRYSTAL_BOOTS.value())
                .add(ItemRegistry.COPPERWOOD_BOOTS.value())
                .add(ItemRegistry.MAGNETIC_BOOTS.value());
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
    }
}
