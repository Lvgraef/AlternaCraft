package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.entity.Ceratosaurus;
import io.github.itskillerluc.alternacraft.entity.Magmatyrannus;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class EntityDataSerailizerRegistry {
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, AlternaCraft.MODID);

    public static final Supplier<EntityDataSerializer<Magmatyrannus.Variant>> MAGMA_TYRANNUS_VARIANT_SERIALIZER = ENTITY_DATA_SERIALIZERS.register("magma_tyrannus_variant_serializer",
            () -> EntityDataSerializer.forValueType(Magmatyrannus.Variant.STREAM_CODEC));

    public static final Supplier<EntityDataSerializer<Ceratosaurus.Variant>> CERATOSAURUS_VARIANT_SERIALIZER = ENTITY_DATA_SERIALIZERS.register("ceratosaurus_variant_serializer",
            () -> EntityDataSerializer.forValueType(Ceratosaurus.Variant.STREAM_CODEC));

}
