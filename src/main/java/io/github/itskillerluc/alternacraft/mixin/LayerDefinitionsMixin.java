package io.github.itskillerluc.alternacraft.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.itskillerluc.alternacraft.util.Util;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LayerDefinitions.class)
@Debug(export = true)
public class LayerDefinitionsMixin {
    @Inject(method = "createRoots", at = @At(value = "TAIL"))
    private static void alternaCraft$createRoots(CallbackInfoReturnable<Map<ModelLayerLocation, LayerDefinition>> cir, @Local ImmutableMap<ModelLayerLocation, LayerDefinition> immutablemap) {
        Util.initializeHeadMap(immutablemap);
    }
}
