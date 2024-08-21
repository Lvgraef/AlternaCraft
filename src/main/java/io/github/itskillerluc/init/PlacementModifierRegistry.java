package io.github.itskillerluc.init;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.itskillerluc.AlternaCraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class PlacementModifierRegistry {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, AlternaCraft.MODID);

    public static final Supplier<PlacementModifierType<EverywhereModifier>> EVERYWHERE = PLACEMENT_MODIFIERS.register("everywhere",
            () -> () -> EverywhereModifier.CODEC);

    public static class EverywhereModifier extends PlacementModifier {
        public static final EverywhereModifier INSTANCE = new EverywhereModifier();
        public static final MapCodec<EverywhereModifier> CODEC = MapCodec.unit(() -> INSTANCE);

        @Override
        public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
            return BlockPos.betweenClosedStream(pos.atY(320), pos.east(15).south(15).atY(-64));
        }

        @Override
        public PlacementModifierType<?> type() {
            return EVERYWHERE.get();
        }
    }
}
