package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Stream;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class PlacedFeatureInit {
    public static final ResourceKey<PlacedFeature> ELECTREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<PlacedFeature> CHARRED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "charred_tree"));
    public static final ResourceKey<PlacedFeature> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));
    public static final ResourceKey<PlacedFeature> DEAD_GRASS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_grass"));
    public static final ResourceKey<PlacedFeature> DEAD_FLOWERS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_flowers"));
    public static final ResourceKey<PlacedFeature> CERULEAN_LAVA = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "cerulean_lava"));
    public static final ResourceKey<PlacedFeature> PAINITE_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "painite_ore"));


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(
                context,
                ELECTREE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.ELECTREE),
                ArrayUtils.add(VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        BlockRegistry.ELECTREE_SAPLING.get()).toArray(new PlacementModifier[0]),
                RandomOffsetPlacement.vertical(UniformInt.of(0, 3)))
        );

        PlacementUtils.register(
                context,
                CHARRED_TREE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.CHARRED_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))
        );

        PlacementUtils.register(
                context,
                FLOWER_ELECTRIC_SWAMP,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.FLOWER_ELECTRIC_SWAMP),
                CountPlacement.of(3),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                DEAD_FLOWERS,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.DEAD_FLOWERS),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                DEAD_GRASS,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.DEAD_GRASS),
                worldSurfaceSquaredWithCount(5));

        PlacementUtils.register(
                context,
                CERULEAN_LAVA,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.CERULEAN_LAVA),
                PlacementModifierRegistry.EverywhereModifier.INSTANCE,
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER, Fluids.FLOWING_WATER)),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                PAINITE_ORE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.PAINITE_ORE),
                CountPlacement.of(
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(7), 2)
                                .add(ConstantInt.of(6), 5)
                                .add(ConstantInt.of(6), 3)
                                .add(ConstantInt.of(3), 2).build())),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(new VerticalAnchor.Absolute(0), new VerticalAnchor.Absolute(60)),
                BiomeFilter.biome()
        );
    }
}
