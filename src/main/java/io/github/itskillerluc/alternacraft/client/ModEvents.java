package io.github.itskillerluc.alternacraft.client;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.client.model.CeratosaurusModel;
import io.github.itskillerluc.alternacraft.client.model.MagmatyrannusModel;
import io.github.itskillerluc.alternacraft.client.renderer.CeratosarusRenderer;
import io.github.itskillerluc.alternacraft.client.renderer.MagmatyrannusRenderer;
import io.github.itskillerluc.duclib.client.model.BaseDucModel;
import io.github.itskillerluc.alternacraft.entity.Ceratosaurus;
import io.github.itskillerluc.alternacraft.entity.Magmatyrannus;
import io.github.itskillerluc.alternacraft.init.EntityRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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
