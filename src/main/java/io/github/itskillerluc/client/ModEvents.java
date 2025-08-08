package io.github.itskillerluc.client;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.client.model.CeratosaurusModel;
import io.github.itskillerluc.client.model.MagmatyrannusModel;
import io.github.itskillerluc.client.renderer.CeratosarusRenderer;
import io.github.itskillerluc.client.renderer.MagmatyrannusRenderer;
import io.github.itskillerluc.duclib.client.model.BaseDucModel;
import io.github.itskillerluc.entity.Ceratosaurus;
import io.github.itskillerluc.entity.Magmatyrannus;
import io.github.itskillerluc.init.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.MAGMATYRANNUS.get(), MagmatyrannusRenderer::new);
        event.registerEntityRenderer(EntityRegistry.CERATOSAURUS.get(), CeratosarusRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MagmatyrannusModel.LAYER_LOCATION, () -> BaseDucModel.getLakeDefinition(Magmatyrannus.LOCATION));
        event.registerLayerDefinition(CeratosaurusModel.LAYER_LOCATION, () -> BaseDucModel.getLakeDefinition(Ceratosaurus.LOCATION));
    }
}
