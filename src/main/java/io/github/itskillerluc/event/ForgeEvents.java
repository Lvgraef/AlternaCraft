package io.github.itskillerluc.event;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.ArmorMaterialRegistry;
import io.github.itskillerluc.init.BiomeInit;
import io.github.itskillerluc.init.ToolTiers;
import io.github.itskillerluc.worldgen.biome.OverworldRegion;
import io.github.itskillerluc.worldgen.biome.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.apache.commons.lang3.stream.Streams;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@EventBusSubscriber(modid = AlternaCraft.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {
    @SubscribeEvent
    public static void livingAttackEvent(final LivingIncomingDamageEvent event) {
        if (event.getSource().is(DamageTypes.FALL)) {
            if (Streams.of(event.getEntity().getArmorSlots().iterator()).allMatch(stack ->
                    stack.getItem() instanceof ArmorItem armorItem && (armorItem.getMaterial().value().equals(ArmorMaterialRegistry.MAGNET.value()) || armorItem.getMaterial().value().equals(ArmorMaterialRegistry.AIO.value())))) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void entityDropEvent(final LivingDropsEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.getMainHandItem().getItem() instanceof TieredItem tieredItem && (tieredItem.getTier() == ToolTiers.MAGNETIC_TIER || tieredItem.getTier() == ToolTiers.AIO_TIER)) {
                event.setCanceled(true);
                for (ItemEntity drop : event.getDrops()) {
                    if (!player.addItem(drop.getItem())) {
                        player.drop(drop.getItem(), false);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void livingBreatheEvent(final LivingBreatheEvent event) {
        if (event.getEntity().level().getBiome(event.getEntity().blockPosition()).is(BiomeInit.ELECTRIC_SWAMP) && event.getEntity().isInWater()
                && Streams.of(event.getEntity().getArmorSlots().iterator()).allMatch(item -> item.getItem() instanceof ArmorItem armor && (armor.getMaterial() == ArmorMaterialRegistry.AIO || armor.getMaterial() == ArmorMaterialRegistry.MAGNET))) {
            event.getEntity().hurt(event.getEntity().damageSources().lightningBolt(), 1);
        }
    }
}
