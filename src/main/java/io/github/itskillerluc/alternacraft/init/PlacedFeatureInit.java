package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.ArrayUtils;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class PlacedFeatureInit {
    public static final ResourceKey<PlacedFeature> ELECTREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<PlacedFeature> BLUE_PASTELIZED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "blue_pastelized_tree"));
    public static final ResourceKey<PlacedFeature> RED_PASTELIZED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "red_pastelized_tree"));
    public static final ResourceKey<PlacedFeature> CHARRED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "charred_tree"));
    public static final ResourceKey<PlacedFeature> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));
    public static final ResourceKey<PlacedFeature> DEAD_GRASS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_grass"));
    public static final ResourceKey<PlacedFeature> DEAD_FLOWERS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dead_flowers"));
    public static final ResourceKey<PlacedFeature> CERULEAN_LAVA = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "cerulean_lava"));
    public static final ResourceKey<PlacedFeature> PAINITE_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "painite_ore"));
    public static final ResourceKey<PlacedFeature> COPPERWOOD_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "copperwood_ore"));
    public static final ResourceKey<PlacedFeature> MAGNETIC_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magnetic_ore"));
    public static final ResourceKey<PlacedFeature> DARK_CRYSTAL_ORE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dark_crystal_ore"));
    public static final ResourceKey<PlacedFeature> CRYSTAL_ICE_CACTUS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "crystal_ice_cactus"));
    public static final ResourceKey<PlacedFeature> COLORFUL_FLOWERS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "colorful_flowers"));
    public static final ResourceKey<PlacedFeature> FLOWER_PETALS = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_petals"));
    public static final ResourceKey<PlacedFeature> ROCK_PATCH = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "rock_patch"));


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
                BLUE_PASTELIZED_TREE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.BLUE_PASTELIZED_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), BlockRegistry.BLUE_PASTELIZED_SAPLING.get())
        );

        PlacementUtils.register(
                context,
                RED_PASTELIZED_TREE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.RED_PASTELIZED_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.1f, 2), BlockRegistry.BLUE_PASTELIZED_SAPLING.get())
        );

        PlacementUtils.register(
                context,
                CHARRED_TREE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.CHARRED_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.1f, 2))
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
                HeightRangePlacement.uniform(new VerticalAnchor.Absolute(0), new VerticalAnchor.Absolute(60)),
                CountPlacement.of(
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(2), 2)
                                .add(ConstantInt.of(3), 5)
                                .add(ConstantInt.of(1), 3)
                                .add(ConstantInt.of(4), 1).build())),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                DARK_CRYSTAL_ORE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.DARK_CRYSTAL_ORE),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(new VerticalAnchor.Absolute(-80), new VerticalAnchor.Absolute(80)),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                COPPERWOOD_ORE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.COPPERWOOD_ORE),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(new VerticalAnchor.Absolute(-80), new VerticalAnchor.Absolute(80)),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                MAGNETIC_ORE,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.MAGNETIC_ORE),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(new VerticalAnchor.Absolute(-80), new VerticalAnchor.Absolute(80)),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                CRYSTAL_ICE_CACTUS,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.CRYSTAL_ICE_CACTUS),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                COLORFUL_FLOWERS,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.COLORFUL_FLOWERS),
                CountPlacement.of(3),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FLOWER_PETALS,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.FLOWER_PETALS),
                CountPlacement.of(UniformInt.of(2, 4)),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                ROCK_PATCH,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.ROCK_PATCH),
                CountPlacement.of(UniformInt.of(1, 2)),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
        );
    }
}
