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
        addBiomeSimilar(mapper, Biomes.MANGROVE_SWAMP, BiomeInit.ELECTRIC_SWAMP);
        addBiomeSimilar(mapper, Biomes.BADLANDS, BiomeInit.VOLCANIC_WASTELAND);
        addBiomeSimilar(mapper, Biomes.ERODED_BADLANDS, BiomeInit.VOLCANIC_WASTELAND);
        addBiomeSimilar(mapper, Biomes.WOODED_BADLANDS, BiomeInit.VOLCANIC_WASTELAND);
        addBiomeSimilar(mapper, Biomes.TAIGA, BiomeInit.COLORFUL_FOREST);
        addBiomeSimilar(mapper, Biomes.OLD_GROWTH_PINE_TAIGA, BiomeInit.COLORFUL_FOREST);
        addBiomeSimilar(mapper, Biomes.OLD_GROWTH_SPRUCE_TAIGA, BiomeInit.COLORFUL_FOREST);
        addBiomeSimilar(mapper, Biomes.SNOWY_PLAINS, BiomeInit.FROZEN_DESERT);
        addBiomeSimilar(mapper, Biomes.OCEAN, Biomes.OCEAN);
        addBiomeSimilar(mapper, Biomes.COLD_OCEAN, Biomes.COLD_OCEAN);
        addBiomeSimilar(mapper, Biomes.DEEP_OCEAN, Biomes.DEEP_OCEAN);
        addBiomeSimilar(mapper, Biomes.LUKEWARM_OCEAN, Biomes.LUKEWARM_OCEAN);
        addBiomeSimilar(mapper, Biomes.FROZEN_OCEAN, Biomes.FROZEN_OCEAN);
        addBiomeSimilar(mapper, Biomes.WARM_OCEAN, Biomes.WARM_OCEAN);
    }
}
