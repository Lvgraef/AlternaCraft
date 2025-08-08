package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.entity.Ceratosaurus;
import io.github.itskillerluc.entity.Magmatyrannus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AlternaCraft.MODID);

    public static final Supplier<EntityType<Magmatyrannus>> MAGMATYRANNUS = ENTITY_TYPES.register("magmatyrannus",
            () -> EntityType.Builder.of(Magmatyrannus::new, MobCategory.CREATURE).sized(2, 4.7f)
                    .build(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magmatyrannus").toString()));

    public static final Supplier<EntityType<Ceratosaurus>> CERATOSAURUS = ENTITY_TYPES.register("ceratosaurus",
            () -> EntityType.Builder.of(Ceratosaurus::new, MobCategory.CREATURE).sized(1.5f, 2.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "ceratosaurus").toString()));
}
