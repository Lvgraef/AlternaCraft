package io.github.itskillerluc.client.event;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.FluidTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.MOD, value = net.neoforged.api.distmarker.Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void RegisterClientExtensions(final RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation COOL_LAVA_STILL = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava_still");
            private static final ResourceLocation COOL_LAVA_FLOWING = ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava_flowing");

            @Override
            public ResourceLocation getFlowingTexture() {
                return COOL_LAVA_FLOWING;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return COOL_LAVA_STILL;
            }
        }, FluidTypeRegistry.COOL_LAVA.get());
    }
}
