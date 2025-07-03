package io.github.itskillerluc.worldgen.biome;

import com.google.common.collect.ImmutableList;
import io.github.itskillerluc.init.BiomeInit;
import io.github.itskillerluc.init.BlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class SurfaceRuleData {
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource VOLCANIC_SOIL = makeStateRule(BlockRegistry.VOLCANIC_SOIL.get());
    private static final SurfaceRules.RuleSource VOLCANIC_ROCK = makeStateRule(BlockRegistry.VOLCANIC_ROCK.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource FROZEN_SAND = makeStateRule(BlockRegistry.FROZEN_SAND.value());
    private static final SurfaceRules.RuleSource MOSSY_GRASS = makeStateRule(BlockRegistry.MOSSY_GRASS.value());
    private static final SurfaceRules.RuleSource MOSSY_DIRT = makeStateRule(BlockRegistry.MOSSY_DIRT.value());

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    public static SurfaceRules.RuleSource overworld() {
        SurfaceRules.RuleSource surfaceRules = SurfaceRules.sequence(
                makeOverworldRules());

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        builder.add(surfaceRules);
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    private static SurfaceRules.RuleSource makeOverworldRules() {
        SurfaceRules.RuleSource swamp = SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(BiomeInit.ELECTRIC_SWAMP),
                        SurfaceRules.ifTrue(
                                SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)
                        )
                )
        );

        SurfaceRules.ConditionSource waterCheck = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource waterCheck1 = SurfaceRules.waterBlockCheck(-6, -1);
        SurfaceRules.ConditionSource waterCheck2 = SurfaceRules.waterBlockCheck(0, 0);


        SurfaceRules.RuleSource volcanicWasteland = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(BiomeInit.VOLCANIC_WASTELAND),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                waterCheck1,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(
                                                waterCheck,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.abovePreliminarySurface(),
                                                        SurfaceRules.ifTrue(
                                                                waterCheck2,
                                                                VOLCANIC_SOIL
                                                        )
                                                )
                                        )

                                )
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.not(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(0))),
                                VOLCANIC_ROCK
                        )
                )
        );

        SurfaceRules.RuleSource frozenDesert = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(BiomeInit.FROZEN_DESERT),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.abovePreliminarySurface(),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                waterCheck,
                                                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), FROZEN_SAND)
                                        )
                                )
                        )
                )
        );

        SurfaceRules.RuleSource colorfulForest = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(BiomeInit.COLORFUL_FOREST),
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.ifTrue(
                                SurfaceRules.UNDER_FLOOR,
                                SurfaceRules.ifTrue(
                                        waterCheck,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.ON_FLOOR,
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.waterBlockCheck(0, 0),
                                                                MOSSY_GRASS
                                                        )
                                                ),
                                                MOSSY_DIRT
                                        )
                                )
                        )
                )
        );

        return SurfaceRules.sequence(swamp, volcanicWasteland, frozenDesert, colorfulForest);
    }


}
