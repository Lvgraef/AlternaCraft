package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DataComponentRegistry {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, AlternaCraft.MODID);

    public static final Supplier<DataComponentType<Double>> DISTANCE = DATA_COMPONENTS.register("distance",
            () -> new DataComponentType.Builder<Double>().networkSynchronized(ByteBufCodecs.DOUBLE).build());

    public static final Supplier<DataComponentType<Long>> GAMETIME = DATA_COMPONENTS.register("gametime",
            () -> new DataComponentType.Builder<Long>().networkSynchronized(ByteBufCodecs.VAR_LONG).build());
}
