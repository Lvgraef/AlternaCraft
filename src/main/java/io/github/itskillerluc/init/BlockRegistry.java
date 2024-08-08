package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.block.CoolLavaBlock;
import io.github.itskillerluc.block.LogBlock;
import io.github.itskillerluc.block.MiningLight;
import io.github.itskillerluc.block.RocksBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AlternaCraft.MODID);


    public static final Supplier<Block> MINING_LIGHT = BLOCKS.registerBlock("mining_light", MiningLight::new, BlockBehaviour.Properties.of().lightLevel(state -> 15).replaceable().instabreak().noCollission().noOcclusion());
    public static final DeferredBlock<RotatedPillarBlock> CHARRED_BARK = BLOCKS.registerBlock("charred_bark", props -> new LogBlock(props, BlockRegistry.STRIPPED_CHARRED_BARK), BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).mapColor(block ->  MapColor.STONE));
    public static final Holder<Block> CHARRED_PLANKS = BLOCKS.registerSimpleBlock("charred_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_GRAY));
    public static final DeferredBlock<RotatedPillarBlock> ELECTREE_LOG = BLOCKS.registerBlock("electree_log", props -> new LogBlock(props, BlockRegistry.STRIPPED_CHARRED_BARK), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LOG).mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_YELLOW : MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELECTREE_LOG = BLOCKS.registerBlock("stripped_electree_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG));
    public static final DeferredBlock<LiquidBlock> COOL_LAVA = BLOCKS.registerBlock("cool_lava", CoolLavaBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).lightLevel(state -> 12));

    public static final DeferredBlock<Block> DEAD_DANDELION = BLOCKS.registerBlock("dead_dandelion",props -> new FlowerBlock(
            MobEffects.WEAKNESS, 7, props), BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));

    public static final DeferredBlock<Block> DEAD_GRASS = BLOCKS.registerBlock("dead_grass",
            TallGrassBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS));

    public static final DeferredBlock<Block> DEAD_POPPY = BLOCKS.registerBlock("dead_poppy",props -> new FlowerBlock(
            MobEffects.WITHER, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> ORANGE_BULB = BLOCKS.registerBlock("orange_bulb", props -> new FlowerBlock(
            MobEffects.REGENERATION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TULIP));

    public static final DeferredBlock<Block> BLUE_BULB = BLOCKS.registerBlock("blue_bulb",props -> new FlowerBlock(
            MobEffects.LEVITATION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID));

    public static final DeferredBlock<Block> GREEN_ROSE_BULB = BLOCKS.registerBlock("green_rose_bulb",props -> new FlowerBlock(
            MobEffects.LEVITATION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.AZURE_BLUET));

    public static final DeferredBlock<Block> PAINITE_ORE_SOIL = BLOCKS.registerSimpleBlock("painite_ore_soil", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHARRED_BARK = BLOCKS.registerBlock("stripped_charred_bark", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).mapColor(block ->  MapColor.STONE));

    public static final DeferredBlock<Block> VOLCANIC_ROCK = BLOCKS.registerSimpleBlock("volcanic_rock", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> VOLCANIC_SOIL = BLOCKS.registerSimpleBlock("volcanic_soil", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final Holder<Block> FROZEN_SAND = BLOCKS.registerBlock("frozen_sand", props -> new ColoredFallingBlock(new ColorRGBA(0x6AB9FFFF), props), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<Block> FROZEN_CACTUS = BLOCKS.registerBlock("frozen_cactus", CactusBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final Holder<Block> DEEPSLATE_DARK_CRYSTAL_ORE = BLOCKS.registerSimpleBlock("deepslate_dark_crystal_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));
    public static final Holder<Block> DARK_CRYSTAL_ORE = BLOCKS.registerSimpleBlock("dark_crystal_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));
    public static final DeferredBlock<Block> ELECTREE_LEAVES = BLOCKS.registerSimpleBlock("electree_leaves", BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).mapColor(MapColor.COLOR_YELLOW));
    public static final DeferredBlock<Block> BLUE_PASTEL_LEAVES = BLOCKS.registerSimpleBlock("blue_pastel_leaves", BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final Holder<Block> ELECTREE_PLANKS = BLOCKS.registerSimpleBlock("electree_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    public static final DeferredBlock<RotatedPillarBlock> BLUE_PASTEL_LOG = BLOCKS.registerBlock("blue_pastel_log", props -> new LogBlock(props, BlockRegistry.STRIPPED_BLUE_PASTEL_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_LIGHT_BLUE : MapColor.PODZOL));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLUE_PASTEL_LOG = BLOCKS.registerBlock("stripped_blue_pastel_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<Block> BLUE_PASTEL_PETALS = BLOCKS.registerBlock("blue_pastel_petals", PinkPetalsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final Holder<Block> BLUE_PASTEL_PLANKS = BLOCKS.registerSimpleBlock("blue_pastel_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_LIGHT_BLUE));
    public static final Holder<Block> COPPERWOOD_ORE = BLOCKS.registerSimpleBlock("copperwood_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));
    public static final Holder<Block> DEEPSLATE_COPPERWOOD_ORE = BLOCKS.registerSimpleBlock("deepslate_copperwood_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));
    public static final DeferredBlock<Block> MOSSY_DIRT = BLOCKS.registerSimpleBlock("mossy_dirt", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final DeferredBlock<Block> MOSSY_GRASS = BLOCKS.registerSimpleBlock("mossy_grass", BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> RED_PASTEL_LEAVES = BLOCKS.registerSimpleBlock("red_pastel_leaves", BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES).mapColor(MapColor.COLOR_RED));
    public static final DeferredBlock<RotatedPillarBlock> RED_PASTEL_LOG = BLOCKS.registerBlock("red_pastel_log", props -> new LogBlock(props, BlockRegistry.STRIPPED_RED_PASTEL_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_RED : MapColor.COLOR_BROWN));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_RED_PASTEL_LOG = BLOCKS.registerBlock("stripped_red_pastel_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG).mapColor(MapColor.TERRACOTTA_RED));
    public static final DeferredBlock<SaplingBlock> ELECTREE_SAPLING = BLOCKS.registerBlock("electree_sapling", props -> new SaplingBlock(TreeGrowers.ELECTREE, props), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

    public static final DeferredBlock<Block> RED_PASTEL_PETALS = BLOCKS.registerBlock("red_pastel_petals", PinkPetalsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS).mapColor(MapColor.COLOR_RED));
    public static final Holder<Block> RED_PASTEL_PLANKS = BLOCKS.registerSimpleBlock("red_pastel_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> ROCKS = BLOCKS.registerBlock("rocks", RocksBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instabreak().noOcclusion().noCollission());
    public static final DeferredBlock<Block> BLUE_COLORFUL_FLOWER = BLOCKS.registerBlock("blue_colorful_flower",props -> new FlowerBlock(
            MobEffects.SATURATION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER));
    public static final DeferredBlock<Block> CYAN_COLORFUL_FLOWER = BLOCKS.registerBlock("cyan_colorful_flower",props -> new FlowerBlock(
            MobEffects.CONFUSION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID));
    public static final DeferredBlock<Block> GREEN_COLORFUL_FLOWER = BLOCKS.registerBlock("green_colorful_flower",props -> new FlowerBlock(
            MobEffects.DIG_SLOWDOWN, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID).mapColor(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> LIME_COLORFUL_FLOWER = BLOCKS.registerBlock("lime_colorful_flower",props -> new FlowerBlock(
            MobEffects.DIG_SPEED, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID).mapColor(DyeColor.LIME));
    public static final DeferredBlock<Block> MAGENTA_COLORFUL_FLOWER = BLOCKS.registerBlock("magenta_colorful_flower",props -> new FlowerBlock(
            MobEffects.MOVEMENT_SPEED, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM));
    public static final DeferredBlock<Block> ORANGE_COLORFUL_FLOWER = BLOCKS.registerBlock("orange_colorful_flower",props -> new FlowerBlock(
            MobEffects.HEAL, 1, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TULIP));
    public static final DeferredBlock<Block> PINK_COLORFUL_FLOWER = BLOCKS.registerBlock("pink_colorful_flower",props -> new FlowerBlock(
            MobEffects.DAMAGE_RESISTANCE, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_TULIP));
    public static final DeferredBlock<Block> PURPLE_COLORFUL_FLOWER = BLOCKS.registerBlock("purple_colorful_flower",props -> new FlowerBlock(
            MobEffects.SATURATION, 4, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM));

    public static final DeferredBlock<Block> YELLOW_COLORFUL_FLOWER = BLOCKS.registerBlock("yellow_colorful_flower",props -> new FlowerBlock(
            MobEffects.FIRE_RESISTANCE, 10, props), BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION));
}
