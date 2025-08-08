package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AlternaCraft.MODID);
    public static final Supplier<FluidType> COOL_LAVA = FLUID_TYPES.register("cool_lava",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("block.alternacraft.cool_lava")
                    .canSwim(false)
                    .canDrown(false)
                    .pathType(PathType.LAVA)
                    .adjacentPathType(null)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .lightLevel(12)
                    .density(3000)
                    .viscosity(6000)
                    .temperature(500)));
}