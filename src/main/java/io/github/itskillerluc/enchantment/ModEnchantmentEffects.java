package io.github.itskillerluc.enchantment;

import com.mojang.serialization.MapCodec;
import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.enchantment.custom.StrikingEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AlternaCraft.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> STRIKING =
            ENTITY_ENCHANTMENT_EFFECTS.register("striking",()-> StrikingEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }

}
