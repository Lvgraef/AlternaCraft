package io.github.itskillerluc.alternacraft.init;

import com.mojang.serialization.MapCodec;
import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.loot.SmeltingLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class GlobalLootModifierRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS.key(), AlternaCraft.MODID);

    public static final Supplier<MapCodec<SmeltingLootModifier>> SMELTING = MODIFIERS.register("smelting",
            SmeltingLootModifier.CODEC);
}
