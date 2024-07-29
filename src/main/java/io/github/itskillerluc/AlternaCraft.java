package io.github.itskillerluc;

import com.mojang.logging.LogUtils;
import io.github.itskillerluc.init.*;
import io.github.itskillerluc.worldgen.biome.OverworldRegion;
import io.github.itskillerluc.worldgen.biome.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(AlternaCraft.MODID)
@SuppressWarnings("unused")
public class AlternaCraft {
    public static final String MODID = "alternacraft";
    private static final Logger LOGGER = LogUtils.getLogger();
    public AlternaCraft(IEventBus modEventBus, ModContainer modContainer) {
        SoundEventRegistry.SOUND_EVENTS.register(modEventBus);
        ArmorMaterialRegistry.ARMOR_MATERIALS.register(modEventBus);
        DataComponentRegistry.DATA_COMPONENTS.register(modEventBus);
        EntityDataSerailizerRegistry.ENTITY_DATA_SERIALIZERS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        Regions.register(new OverworldRegion(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "overworld"), RegionType.OVERWORLD, 3));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, SurfaceRuleData.overworld());

        BlockRegistry.BLOCKS.register(modEventBus);
        FluidRegistry.FLUIDS.register(modEventBus);
        FluidTypeRegistry.FLUID_TYPES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        CreativeTabRegistry.CREATIVE_TABS.register(modEventBus);
        GlobalLootModifierRegistry.MODIFIERS.register(modEventBus);
    }
}
