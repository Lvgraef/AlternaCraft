package io.github.itskillerluc.alternacraft.client.renderer;

import io.github.itskillerluc.alternacraft.client.model.CeratosaurusModel;
import io.github.itskillerluc.duclib.client.model.Ducling;
import io.github.itskillerluc.alternacraft.entity.Ceratosaurus;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CeratosarusRenderer extends MobRenderer<Ceratosaurus, CeratosaurusModel> {
    public CeratosarusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CeratosaurusModel((Ducling) pContext.bakeLayer(CeratosaurusModel.LAYER_LOCATION)), 1.5f);
    }

    @Override
    public boolean shouldRender(Ceratosaurus pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return super.shouldRender(pLivingEntity, pCamera, pCamX, pCamY, pCamZ);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Ceratosaurus pEntity) {
        return pEntity.getVariant().getTexture();
    }
}
