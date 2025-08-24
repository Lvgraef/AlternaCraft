package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.worldgen.biome.OverworldBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BiomeInit {

    public static final ResourceKey<Biome> ELECTRIC_SWAMP = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "electric_swamp"));
    public static final ResourceKey<Biome> VOLCANIC_WASTELAND = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "volcanic_wasteland"));
    public static final ResourceKey<Biome> FROZEN_DESERT = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "frozen_desert"));
    public static final ResourceKey<Biome> COLORFUL_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "colorful_forest"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> holdergetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(ELECTRIC_SWAMP, OverworldBiomes.electricSwamp(holdergetter, holdergetter1));
        context.register(VOLCANIC_WASTELAND, OverworldBiomes.volcanicWasteland(holdergetter, holdergetter1));
        context.register(FROZEN_DESERT, OverworldBiomes.frozenDesert(holdergetter, holdergetter1));
        context.register(COLORFUL_FOREST, OverworldBiomes.colorfulForest(holdergetter, holdergetter1));
    }
}
