package io.github.itskillerluc.alternacraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ConsumableProperties {
    public static final FoodProperties ALTERNABERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 80),1f).fast().build();

    public static final FoodProperties SHOCKBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400),1f).fast().build();

    public static final FoodProperties CHERRY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3f).fast().build();

    public static final FoodProperties RAW_DINO = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).build();

    public static final FoodProperties COOKED_DINO = new FoodProperties.Builder().nutrition(10).saturationModifier(1f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100),1f).build();
}
