package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.worldgen.tree.HangingLeavesFoliagePlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FoliagePlacerRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, AlternaCraft.MODID);

    public static final Supplier<FoliagePlacerType<HangingLeavesFoliagePlacer>> HANGING_LEAVES_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("hanging_leaves_foliage_placer", () -> new FoliagePlacerType<>(HangingLeavesFoliagePlacer.CODEC));
}
