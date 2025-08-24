package io.github.itskillerluc.alternacraft.client.event;


import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.init.AttachmentTypeRegistry;
import io.github.itskillerluc.alternacraft.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.joml.Quaternionf;

import java.util.Map;


@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class NeoForgeEvents {
    private static final ResourceLocation CHICKEN_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/chicken.png");
    private static final Lazy<ChickenModel<Chicken>> CHICKEN_MODEL = Lazy.of(() -> {
        ChickenModel<Chicken> model = new ChickenModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.CHICKEN));
        model.young = true;
        return model;
    });

    @SubscribeEvent
    public static void turnPlayer(final CalculatePlayerTurnEvent event) {
        if (Minecraft.getInstance().player != null) {
            if (Minecraft.getInstance().player.getData(AttachmentTypeRegistry.STUNNED)) {
                event.setMouseSensitivity(-1 / 3f);
            }
        }
    }

    @SubscribeEvent
    public static void inputEvent(final InputEvent.InteractionKeyMappingTriggered event) {
        if (Minecraft.getInstance().player != null) {
            if (Minecraft.getInstance().player.getData(AttachmentTypeRegistry.STUNNED)) {
                event.setSwingHand(false);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void movementEvent(final MovementInputUpdateEvent event) {
        if (Minecraft.getInstance().player != null) {
            if (Minecraft.getInstance().player.getData(AttachmentTypeRegistry.STUNNED)) {
                event.getInput().up = false;
                event.getInput().down = false;
                event.getInput().left = false;
                event.getInput().right = false;
                event.getInput().jumping = false;
                event.getInput().shiftKeyDown = false;
                event.getInput().forwardImpulse = 0;
                event.getInput().leftImpulse = 0;
            }
        }
    }


    @SubscribeEvent
    public static void RenderLivingEvent(final RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
        var head = Util.HEAD_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey() == event.getEntity().getClass())
                .findFirst().map(Map.Entry::getValue).orElse(null);



        var poseStack = event.getPoseStack();
        for (float i = -0.5f; i <= 0.5; i = i + 1) {
            for (float j = -0.5f; j <= 0.5; j = j + 1) {
                poseStack.pushPose();
                poseStack.mulPose(new Quaternionf().rotateX(Mth.PI));
                poseStack.mulPose(new Quaternionf().rotateY((Minecraft.getInstance().level.getGameTime() + event.getPartialTick()) * 0.3f));

                float xOffset = 0, yOffset = 0, zOffset = 0;

//                if (head != null) {
//                    PartPose headPos = head.partPose;
//                    xOffset = headPos.x;
//                    yOffset = headPos.y;
//                    zOffset = headPos.z * 16;
//                }
                poseStack.translate(i + xOffset, -3 + yOffset, j + zOffset);

                var camera = Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation();
                float fx = 2 * (camera.x * camera.z + camera.w * camera.y);
                float fz = 1 - 2 * (camera.x * camera.x + camera.y * camera.y);
                float yaw = (float) Math.atan2(fx, fz);
                poseStack.mulPose(new Quaternionf().rotateY((-yaw) - ((Minecraft.getInstance().level.getGameTime() + event.getPartialTick()) * 0.3f)));
                CHICKEN_MODEL.get().renderToBuffer(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityCutout(CHICKEN_LOCATION)),
                        event.getPackedLight(), OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }
        }
    }
}
