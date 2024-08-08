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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;

public class ConfiguredFeatureInit {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELECTREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));

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
    }
}
