package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.fluid.CoolLavaFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, AlternaCraft.MODID);
    public static final Supplier<Fluid> COOL_LAVA = FLUIDS.register("cool_lava",
            () -> new CoolLavaFluid.Source(FluidRegistry.COOL_LAVA_PROPERTIES) {});
    public static final Supplier<Fluid> COOL_LAVA_FLOWING = FLUIDS.register("cool_lava_flowing",
            () -> new CoolLavaFluid.Flowing(FluidRegistry.COOL_LAVA_PROPERTIES));

    public static final BaseFlowingFluid.Properties COOL_LAVA_PROPERTIES = new BaseFlowingFluid.Properties(FluidTypeRegistry.COOL_LAVA, FluidRegistry.COOL_LAVA, FluidRegistry.COOL_LAVA_FLOWING)
            .bucket(ItemRegistry.COOL_LAVA_BUCKET).block(BlockRegistry.COOL_LAVA);
}
