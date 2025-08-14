package io.github.itskillerluc.event;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.entity.Ceratosaurus;
import io.github.itskillerluc.entity.Magmatyrannus;
import io.github.itskillerluc.init.EntityRegistry;
import io.github.itskillerluc.init.FluidTypeRegistry;
import io.github.itskillerluc.networking.ParticlePayload;
import io.github.itskillerluc.networking.SetStunnedPayload;
import io.github.itskillerluc.networking.SoundPayload;
import io.github.itskillerluc.worldgen.biome.OverworldRegion;
import io.github.itskillerluc.worldgen.biome.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import javax.swing.text.html.parser.Entity;

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

    @SubscribeEvent
    public static void registerEntityAttributes(final RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation COOL_LAVA_STILL = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava_still");
            private static final ResourceLocation COOL_LAVA_FLOWING = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava_flowing");

            @Override
            public ResourceLocation getFlowingTexture(FluidStack stack) {
                return COOL_LAVA_STILL;
            }

            @Override
            public ResourceLocation getStillTexture(FluidStack stack) {
                return COOL_LAVA_FLOWING;
            }
        }, FluidTypeRegistry.COOL_LAVA.get());
    }
}
