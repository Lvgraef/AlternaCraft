package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.worldgen.tree.HangingLeavesFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.Optional;

public class ConfiguredFeatureInit {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELECTREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_PASTELIZED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "blue_pastelized_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_PASTELIZED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "red_pastelized_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHARRED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "charred_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_grass"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_FLOWERS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_flowers"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CERULEAN_LAVA = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "cerulean_lava"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAINITE_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "painite_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_CRYSTAL_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dark_crystal_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> COPPERWOOD_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "copperwood_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNETIC_ORE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magnetic_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_ICE_CACTUS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "crystal_ice_cactus"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLORFUL_FLOWERS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "colorful_flowers"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_PETALS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_petals"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_PATCH = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "rock_patch"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ELECTREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.ELECTREE_LOG.get()),
                new StraightTrunkPlacer(5, 2, 3),
                BlockStateProvider.simple(BlockRegistry.ELECTREE_LEAVES.get()),
                new PineFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(3, 4)),
                Optional.of(
                        new MangroveRootPlacer(
                                UniformInt.of(0, 2),
                                BlockStateProvider.simple(BlockRegistry.ELECTREE_LOG.get()),
                                Optional.empty(),
                                new MangroveRootPlacement(
                                        context.lookup(Registries.BLOCK).getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                                        HolderSet.direct(BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.MUD), BlockRegistry.ELECTREE_LOG),
                                        BlockStateProvider.simple(BlockRegistry.ELECTREE_LOG.get()),
                                        2,
                                        4,
                                        0.1F
                                )
                        )
                ),
                new TwoLayersFeatureSize(2, 0, 2)
        ).decorators(List.of(new LeaveVineDecorator(0.3f))).build()));

        context.register(CHARRED_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegistry.CHARRED_BARK.get()),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(Blocks.AIR),
                new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                Optional.empty(),
                new TwoLayersFeatureSize(2, 0, 2)
        ).build()));

        context.register(FLOWER_ELECTRIC_SWAMP, new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(
                        HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.RANDOM_PATCH,
                                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.BLUE_BULB.get())))
                                ),
                                PlacementUtils.inlinePlaced(
                                        Feature.RANDOM_PATCH,
                                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.ORANGE_BULB.get())))
                                ),
                                PlacementUtils.inlinePlaced(
                                        Feature.RANDOM_PATCH,
                                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.GREEN_ROSE_BULB.get()))
                                        )
                                )
                        ))));

        FeatureUtils.register(context, DEAD_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(
                32, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.DEAD_GRASS.get())))));

        FeatureUtils.register(
                context,
                DEAD_FLOWERS,
                Feature.FLOWER,
                FeatureUtils.simpleRandomPatchConfiguration(32,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder().add(BlockRegistry.DEAD_DANDELION.get().defaultBlockState(), 2).add(BlockRegistry.DEAD_POPPY.get().defaultBlockState(), 1)))
                        )

                ));

        FeatureUtils.register(
                context,
                CERULEAN_LAVA,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.COOL_LAVA.get()))
        );

        FeatureUtils.register(
                context,
                PAINITE_ORE,
                Feature.ORE,
                new OreConfiguration(new BlockMatchTest(BlockRegistry.VOLCANIC_ROCK.get()), BlockRegistry.PAINITE_ORE.get().defaultBlockState(), 3)
        );

        FeatureUtils.register(
                context,
                DARK_CRYSTAL_ORE,
                Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(new BlockMatchTest(Blocks.STONE), BlockRegistry.DARK_CRYSTAL_ORE.value().defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE.value().defaultBlockState())),
                        3)
        );

        FeatureUtils.register(
                context,
                COPPERWOOD_ORE,
                Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(new BlockMatchTest(Blocks.STONE), BlockRegistry.COPPERWOOD_ORE.value().defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), BlockRegistry.DEEPSLATE_COPPERWOOD_ORE.value().defaultBlockState())),
                        3)
        );

        FeatureUtils.register(
                context,
                MAGNETIC_ORE,
                Feature.ORE,
                new OreConfiguration(List.of(
                        OreConfiguration.target(new BlockMatchTest(Blocks.STONE), BlockRegistry.MAGNETIC_ORE.value().defaultBlockState()),
                        OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), BlockRegistry.DEEPSLATE_MAGNETIC_ORE.value().defaultBlockState())),
                        3)
        );

        FeatureUtils.register(
                context,
                CRYSTAL_ICE_CACTUS,
                Feature.RANDOM_PATCH,
                FeatureUtils.simpleRandomPatchConfiguration(
                        10,
                        PlacementUtils.inlinePlaced(
                                Feature.BLOCK_COLUMN,
                                BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(BlockRegistry.FROZEN_CACTUS.get())),
                                BlockPredicateFilter.forPredicate(
                                        BlockPredicate.allOf(
                                                BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(BlockRegistry.FROZEN_CACTUS.get().defaultBlockState(), BlockPos.ZERO)
                                        )
                                )
                        )
                )
        );

        FeatureUtils.register(
                context,
                COLORFUL_FLOWERS,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        96,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new NoiseProvider(
                                                2345L,
                                                new NormalNoise.NoiseParameters(0, 1.0),
                                                0.020833334F,
                                                List.of(
                                                        BlockRegistry.CYAN_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.BLUE_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.GREEN_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.LIME_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.ORANGE_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.PINK_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.PURPLE_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.YELLOW_COLORFUL_FLOWER.get().defaultBlockState(),
                                                        BlockRegistry.MAGENTA_COLORFUL_FLOWER.get().defaultBlockState()
                                                )
                                        )
                                )
                        )
                )
        );

        FeatureUtils.register(
                context,
                FLOWER_PETALS,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new NoiseProvider(
                                324L,
                                new NormalNoise.NoiseParameters(0, 1.0),
                                0.020833334F,
                                List.of(
                                        BlockRegistry.BLUE_PASTEL_PETALS.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, RandomSource.create().nextIntBetweenInclusive(1, 4)).setValue(PinkPetalsBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(RandomSource.create())),
                                        BlockRegistry.RED_PASTEL_PETALS.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, RandomSource.create().nextIntBetweenInclusive(1, 4)).setValue(PinkPetalsBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(RandomSource.create()))
                                )
                        )
                )
        );

        FeatureUtils.register(
                context,
                ROCK_PATCH,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.ROCKS.get()))
        );

        FeatureUtils.register(
                context,
                BLUE_PASTELIZED_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BlockRegistry.BLUE_PASTEL_LOG.get()),
                        new StraightTrunkPlacer(6, 4, 0),
                        BlockStateProvider.simple(BlockRegistry.BLUE_PASTEL_LEAVES.get()),
                        new HangingLeavesFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 2,0.35f),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .ignoreVines()
                        .build()
        );

        FeatureUtils.register(
                context,
                RED_PASTELIZED_TREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BlockRegistry.RED_PASTEL_LOG.get()),
                        new StraightTrunkPlacer(6, 4, 0),
                        BlockStateProvider.simple(BlockRegistry.RED_PASTEL_LEAVES.get()),
                        new HangingLeavesFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 2,0.35f),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .ignoreVines()
                        .build()
        );
    }
}
