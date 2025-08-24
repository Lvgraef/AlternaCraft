package io.github.itskillerluc.alternacraft.event;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.entity.Ceratosaurus;
import io.github.itskillerluc.alternacraft.entity.Magmatyrannus;
import io.github.itskillerluc.alternacraft.init.EntityRegistry;
import io.github.itskillerluc.alternacraft.networking.ParticlePayload;
import io.github.itskillerluc.alternacraft.networking.SetStunnedPayload;
import io.github.itskillerluc.alternacraft.networking.SoundPayload;
import io.github.itskillerluc.alternacraft.worldgen.biome.OverworldRegion;
import io.github.itskillerluc.alternacraft.worldgen.biome.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event){
        event.put(EntityRegistry.MAGMATYRANNUS.get(), Magmatyrannus.attributes().build());
        event.put(EntityRegistry.CERATOSAURUS.get(), Ceratosaurus.attributes().build());
    }

    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                ParticlePayload.TYPE,
                ParticlePayload.STREAM_CODEC,
                ParticlePayload::handleData
        );
        registrar.playToClient(
                SoundPayload.TYPE,
                SoundPayload.STREAM_CODEC,
                SoundPayload::handleData
        );

        registrar.playToClient(
                SetStunnedPayload.TYPE,
                SetStunnedPayload.STREAM_CODEC,
                SetStunnedPayload::handleData
        );
    }



    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new OverworldRegion(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "overworld"), RegionType.OVERWORLD, 3));
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, AlternaCraft.MODID, SurfaceRuleData.overworld());
        });
    }
}
