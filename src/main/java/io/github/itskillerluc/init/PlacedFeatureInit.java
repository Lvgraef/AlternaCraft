package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.apache.commons.lang3.ArrayUtils;

public class PlacedFeatureInit {
    public static final ResourceKey<PlacedFeature> ELECTREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electree"));
    public static final ResourceKey<PlacedFeature> FLOWER_ELECTRIC_SWAMP = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "flower_electric_swamp"));


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
                FLOWER_ELECTRIC_SWAMP,
                configuredFeatures.getOrThrow(ConfiguredFeatureInit.FLOWER_ELECTRIC_SWAMP),
                CountPlacement.of(3),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );
    }
}
