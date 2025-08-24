package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.effects.StunEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, AlternaCraft.MODID);

    public static final Supplier<StunEffect> STUNNED = EFFECTS.register("stunned",
            () -> new StunEffect(MobEffectCategory.HARMFUL, 0x806043));
}
