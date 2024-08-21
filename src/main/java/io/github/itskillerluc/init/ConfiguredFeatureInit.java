package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;

public class ConfiguredFeatureInit {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELECTREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHARRED_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "charred_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_grass"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_FLOWERS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_flowers"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CERULEAN_LAVA = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "cerulean_lava"));

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
    }
}
