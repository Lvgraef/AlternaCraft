package io.github.itskillerluc.alternacraft.client.renderer;

import io.github.itskillerluc.alternacraft.client.model.MagmatyrannusModel;
import io.github.itskillerluc.duclib.client.model.Ducling;
import io.github.itskillerluc.alternacraft.entity.Magmatyrannus;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MagmatyrannusRenderer extends MobRenderer<Magmatyrannus, MagmatyrannusModel> {
    public MagmatyrannusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MagmatyrannusModel((Ducling) pContext.bakeLayer(MagmatyrannusModel.LAYER_LOCATION)), 1.5f);
    }

    @Override
    public boolean shouldRender(Magmatyrannus pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return super.shouldRender(pLivingEntity, pCamera, pCamX, pCamY, pCamZ);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Magmatyrannus pEntity) {
        return pEntity.getVariant().getTexture();
    }
}
