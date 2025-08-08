package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class PaintingRegistry {
    public static final ResourceKey<PaintingVariant> LUC_WHEN_CODING = ResourceKey.create(Registries.PAINTING_VARIANT,
            ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "luc_when_coding"));

    private static final ResourceLocation LUC_WHEN_CODING_TEXTURE = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "luc_when_coding");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        context.register(LUC_WHEN_CODING, new PaintingVariant(3, 4, LUC_WHEN_CODING_TEXTURE));
    }
}
