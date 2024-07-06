package io.github.itskillerluc.client.model;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.duclib.client.model.AnimatableDucModel;
import io.github.itskillerluc.duclib.client.model.Ducling;
import io.github.itskillerluc.entity.Magmatyrannus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class MagmatyrannusModel extends AnimatableDucModel<Magmatyrannus> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magmatyrannus"), "main");

    public MagmatyrannusModel(Ducling ducling) {
        super(ducling, RenderType::entityCutout);
    }

    @Override
    protected Set<String> excludeAnimations() {
        return Set.of("animation.magmatyrannus.walk", "animation.magmatyrannus.run");
    }

    @Override
    public void setupAnim(@NotNull Magmatyrannus pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        if (pEntity.getEntityData().get(Magmatyrannus.RUNNING)) {
            animateWalk(pEntity.getAnimation().getAnimations().get("animation.magmatyrannus.run").animation(), pLimbSwing, pLimbSwingAmount, 2, 1f);
        } else {
            animateWalk(pEntity.getAnimation().getAnimations().get("animation.magmatyrannus.walk").animation(), pLimbSwing, pLimbSwingAmount, 2, 1f);
        }
    }
}
