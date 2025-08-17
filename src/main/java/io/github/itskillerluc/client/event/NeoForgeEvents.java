package io.github.itskillerluc.client.event;


import com.google.common.base.Suppliers;
import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.AttachmentTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.CalculatePlayerTurnEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.GAME, value = net.neoforged.api.distmarker.Dist.CLIENT)
public class NeoForgeEvents {
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

    private static final ResourceLocation CHICKEN_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/chicken.png");
    private static final Supplier<ChickenModel<Chicken>> CHICKEN_MODEL = Suppliers.memoize(() -> {
        ChickenModel<Chicken> model = new ChickenModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.CHICKEN));
        model.young = true;
        return model;
    });


    @SubscribeEvent
    public static void renderEvent(final RenderPlayerEvent.Post event) {
        var poseStack = event.getPoseStack();
        for (float i = -0.5f; i <= 0.5; i = i + 1) {
            for (float j = -0.5f; j <= 0.5; j = j + 1) {
                poseStack.pushPose();
                poseStack.mulPose(new Quaternionf().rotateX(Mth.PI));
                poseStack.mulPose(new Quaternionf().rotateY((Minecraft.getInstance().level.getGameTime() + event.getPartialTick())* 0.3f));
                poseStack.translate(i, -3, j);
                var camera =  Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation();
                float fx = 2 * (camera.x * camera.z + camera.w * camera.y);
                float fz = 1 - 2 * (camera.x * camera.x + camera.y * camera.y);
                float yaw = (float) Math.atan2(fx, fz);
                poseStack.mulPose(new Quaternionf().rotateY((-yaw) - ((Minecraft.getInstance().level.getGameTime() + event.getPartialTick())* 0.3f)));
                CHICKEN_MODEL.get().renderToBuffer(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityCutout(CHICKEN_LOCATION)),
                        event.getPackedLight(), OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }
        }
    }
}
