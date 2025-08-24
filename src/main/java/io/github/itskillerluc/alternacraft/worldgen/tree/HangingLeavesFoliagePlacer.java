package io.github.itskillerluc.alternacraft.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.itskillerluc.alternacraft.init.FoliagePlacerRegistry;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class HangingLeavesFoliagePlacer extends BlobFoliagePlacer {
    private float hangChance;

    public static final MapCodec<HangingLeavesFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> blobParts(instance).and(Codec.FLOAT.fieldOf("hangChacne").forGetter(x -> x.hangChance)).apply(instance, HangingLeavesFoliagePlacer::new)
    );

    public HangingLeavesFoliagePlacer(IntProvider radius, IntProvider offset, int height, float hangChance) {
        super(radius, offset, height);
        this.hangChance = hangChance;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return FoliagePlacerRegistry.HANGING_LEAVES_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader levelReader,
            FoliagePlacer.FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration configuration,
            int maxFreeTreeHeight,
            FoliagePlacer.FoliageAttachment attachment,
            int height,
            int radius,
            int offset
    ) {
        for (int i = offset; i >= offset - height; i--) {
            int j = radius + (i != offset && i != offset - height ? 1 : 0);
            this.placeLeavesRow(levelReader, foliageSetter, random, configuration, attachment.pos(), j, i, attachment.doubleTrunk());
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        if (localY == -height) {
            if (random.nextFloat() > hangChance) {
                return true;
            }
        }
        return Mth.square((float)localX + 0.5F) + Mth.square((float)localZ + 0.5F) > (float)(range * range);
    }
}
