package io.github.itskillerluc.alternacraft.client.model;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.duclib.client.model.AnimatableDucModel;
import io.github.itskillerluc.duclib.client.model.Ducling;
import io.github.itskillerluc.alternacraft.entity.Ceratosaurus;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CeratosaurusModel extends AnimatableDucModel<Ceratosaurus> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "ceratosaurus"), "main");

    public CeratosaurusModel(Ducling ducling) {
        super(ducling, RenderType::entityCutout);
    }

    @Override
    protected Set<String> excludeAnimations() {
        return Set.of("walk", "run");
    }

    @Override
    public void setupAnim(@NotNull Ceratosaurus pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        if (pEntity.getEntityData().get(Ceratosaurus.RUNNING)) {
            float maxAnimationSpeed = 1.125f;
            synchronizeDummyState(pEntity, pLimbSwing, maxAnimationSpeed, "run");
            animateWalk(pEntity.getAnimation().getAnimations().get("run").animation(), pLimbSwing, pLimbSwingAmount, maxAnimationSpeed, 1f);
        } else {
            float maxAnimationSpeed = 8f;
            synchronizeDummyState(pEntity, pLimbSwing, maxAnimationSpeed, "walk");
            animateWalk(pEntity.getAnimation().getAnimations().get("walk").animation(), pLimbSwing, pLimbSwingAmount, maxAnimationSpeed, 3);
        }
    }

    private void synchronizeDummyState(Ceratosaurus pEntity, float limbSwing, float maxAnimationSpeed, String animation) {
        pEntity.getAnimationState(animation).ifPresent(state -> {
            state.start(pEntity.tickCount);
            state.accumulatedTime = (long) (getElapsedSeconds(pEntity.getAnimation().getAnimations().get(animation).animation(), (long) (limbSwing * 50.0F * maxAnimationSpeed)) * 1000L);
        });
    }

    private static float getElapsedSeconds(AnimationDefinition animationDefinition, long accumulatedTime) {
        float f = (float)accumulatedTime / 1000.0F;
        return animationDefinition.looping() ? f % animationDefinition.lengthInSeconds() : f;
    }
}
