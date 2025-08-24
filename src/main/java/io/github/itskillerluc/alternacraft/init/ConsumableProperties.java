package io.github.itskillerluc.alternacraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ConsumableProperties {
    public static final FoodProperties ALTERNABERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40),0.5f).fast().build();
}
