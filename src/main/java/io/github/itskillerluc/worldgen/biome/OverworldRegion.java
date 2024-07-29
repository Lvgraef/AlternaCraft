package io.github.itskillerluc.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import io.github.itskillerluc.init.BiomeInit;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class OverworldRegion extends Region {
    public OverworldRegion(ResourceLocation name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiomeSimilar(mapper, Biomes.SWAMP, BiomeInit.ELECTRIC_SWAMP);
        addBiomeSimilar(mapper, Biomes.BADLANDS, BiomeInit.VOLCANIC_WASTELAND);
        addBiomeSimilar(mapper, Biomes.TAIGA, BiomeInit.COLORFUL_FOREST);
        addBiomeSimilar(mapper, Biomes.SNOWY_PLAINS, BiomeInit.FROZEN_DESERT);
    }
}
