package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AlternaCraft.MODID);

    public static final Supplier<Item> COPPERWOOD_CRYSTAL = ITEMS.registerSimpleItem("copperwood_crystal");
    public static final Supplier<Item> DARK_CRYSTAL = ITEMS.registerSimpleItem("dark_crystal");
    public static final Supplier<Item> MAGNETIC_CRYSTAL = ITEMS.registerSimpleItem("magnetic_crystal");
    public static final Supplier<Item> PAINITE_CRYSTAL = ITEMS.registerSimpleItem("painite_crystal");
    public static final Supplier<Item> AIO_CRYSTAL = ITEMS.registerSimpleItem("aio_crystal");
    public static final Supplier<Item> MINI_STAR = ITEMS.registerSimpleItem("mini_star");
    public static final Supplier<Item> RUBY = ITEMS.registerSimpleItem("ruby");
    public static final Supplier<Item> SAPPHIRE = ITEMS.registerSimpleItem("sapphire");

    public static final Supplier<Item> PRISAMOND_SHARD = ITEMS.registerSimpleItem("prisamond_shard");
    public static final Supplier<Item> PRISMA_STICK = ITEMS.registerSimpleItem("prisma_stick");

    public static final Supplier<Item> ALTERNABERRIES = ITEMS.register("alternaberry",
            () -> new ItemNameBlockItem(BlockRegistry.COLORFUL_BUSH.get(), new Item.Properties().food(ConsumableProperties.ALTERNABERRY)));
    public static final Supplier<Item> SHOCKBERRY = ITEMS.registerItem("shockberry", Item::new, new Item.Properties().food(ConsumableProperties.SHOCKBERRY));
    public static final Supplier<Item> CHERRY = ITEMS.registerItem("cherries", Item::new, new Item.Properties().food(ConsumableProperties.CHERRY));
    public static final Supplier<Item> RAW_DINO = ITEMS.registerItem("raw_dino", Item::new, new Item.Properties().food(ConsumableProperties.RAW_DINO));
    public static final Supplier<Item> COOKED_DINO = ITEMS.registerItem("cooked_dino", Item::new, new Item.Properties().food(ConsumableProperties.COOKED_DINO));

    public static final DeferredHolder<Item, ArmorItem> COPPERWOOD_HELMET = registerArmorWithDescription("copperwood_helmet",
            ArmorMaterialRegistry.COPPERWOOD, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorEffectItem> COPPERWOOD_CHESTPLATE = ITEMS.register("copperwood_chestplate",
            () -> new ArmorEffectItem("copperwood_chestplate", ArmorMaterialRegistry.COPPERWOOD, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1), new MobEffectInstance(MobEffects.REGENERATION, 0, 0, true, false)));
    public static final DeferredHolder<Item, ArmorItem> COPPERWOOD_LEGGINGS = registerArmorWithDescription("copperwood_leggings",
            ArmorMaterialRegistry.COPPERWOOD, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> COPPERWOOD_BOOTS = registerArmorWithDescription("copperwood_boots",
            ArmorMaterialRegistry.COPPERWOOD, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> DARK_CRYSTAL_HELMET = registerArmorWithDescription("dark_crystal_helmet",
            ArmorMaterialRegistry.DARK_CRYSTAL, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorEffectItem> DARK_CRYSTAL_CHESTPLATE = ITEMS.register("dark_crystal_chestplate",
            () -> new ArmorEffectItem("dark_crystal_chestplate", ArmorMaterialRegistry.DARK_CRYSTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0, 0, true, false)));
    public static final DeferredHolder<Item, ArmorItem> DARK_CRYSTAL_LEGGINGS = registerArmorWithDescription("dark_crystal_leggings",
            ArmorMaterialRegistry.DARK_CRYSTAL, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> DARK_CRYSTAL_BOOTS = registerArmorWithDescription("dark_crystal_boots",
            ArmorMaterialRegistry.DARK_CRYSTAL, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> MAGNETIC_HELMET = registerArmorWithDescription("magnetic_helmet",
            ArmorMaterialRegistry.MAGNET, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> MAGNETIC_CHESTPLATE = registerArmorWithDescription("magnetic_chestplate",
            ArmorMaterialRegistry.MAGNET, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> MAGNETIC_LEGGINGS = registerArmorWithDescription("magnetic_leggings",
            ArmorMaterialRegistry.MAGNET, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> MAGNETIC_BOOTS = registerArmorWithDescription("magnetic_boots",
            ArmorMaterialRegistry.MAGNET, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> PAINITE_HELMET = registerArmorWithDescription("painite_helmet",
            ArmorMaterialRegistry.PAINITE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, ArmorEffectItem> PAINITE_CHESTPLATE = ITEMS.register("painite_chestplate",
            () -> new ArmorEffectItem("painite_chestplate", ArmorMaterialRegistry.PAINITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1, 0, true, false)));
    public static final DeferredHolder<Item, ArmorItem> PAINITE_LEGGINGS = registerArmorWithDescription("painite_leggings",
            ArmorMaterialRegistry.PAINITE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> PAINITE_BOOTS = registerArmorWithDescription("painite_boots",
            ArmorMaterialRegistry.PAINITE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> AIO_HELMET = registerArmorWithDescription("aio_helmet",
            ArmorMaterialRegistry.AIO, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, ArmorEffectItem> AIO_CHESTPLATE = ITEMS.register("aio_chestplate",
            () -> new ArmorEffectItem("aio_chestplate", ArmorMaterialRegistry.AIO, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1), new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1, 1, true, false), new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1, 0, false, false), new MobEffectInstance(MobEffects.REGENERATION, 1, 1, false, false)));
    public static final DeferredHolder<Item, ArmorItem> AIO_LEGGINGS = registerArmorWithDescription("aio_leggings",
            ArmorMaterialRegistry.AIO, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, ArmorItem> AIO_BOOTS = registerArmorWithDescription("aio_boots",
            ArmorMaterialRegistry.AIO, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1));
    public static final DeferredHolder<Item, PickaxeItem> AIO_PICKAXE = ITEMS.registerItem("aio_pickaxe",
            properties -> new AIOPickaxe(ToolTiers.AIO_TIER, 5, -3, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, AxeItem> AIO_AXE = ITEMS.registerItem("aio_axe",
            properties -> new AIOAxe(ToolTiers.AIO_TIER, 34, -2.7f, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, SwordItem> AIO_SWORD = ITEMS.registerItem("aio_sword",
            properties -> new AIOSword(ToolTiers.AIO_TIER, 27, -2, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, HoeItem> AIO_HOE = ITEMS.registerItem("aio_scythe",
            properties -> new AIOScythe(ToolTiers.AIO_TIER, 22, 0, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, ShovelItem> AIO_SHOVEL = ITEMS.registerItem("aio_shovel",
            properties -> new AIOShovel(ToolTiers.AIO_TIER, 3, -3, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, PickaxeItem> DARK_CRYSTAL_PICKAXE = ITEMS.registerItem("dark_crystal_pickaxe",
            properties -> new DarkCrystalPickaxe(ToolTiers.DARK_CRYSTAL_TIER, 1, -2.8f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, AxeItem> DARK_CRYSTAL_AXE = ITEMS.registerItem("dark_crystal_axe",
            properties -> new DarkCrystalAxe(ToolTiers.DARK_CRYSTAL_TIER, 14, -3.3f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, SwordItem> DARK_CRYSTAL_SWORD = ITEMS.registerItem("dark_crystal_sword",
            properties -> new DarkCrystalSword(ToolTiers.DARK_CRYSTAL_TIER, 7, -2.4f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, HoeItem> DARK_CRYSTAL_HOE = ITEMS.registerItem("dark_crystal_hoe",
            properties -> new DarkCrystalHoe(ToolTiers.DARK_CRYSTAL_TIER, 0, 0, properties.stacksTo(1)));
    public static final DeferredHolder<Item, ShovelItem> DARK_CRYSTAL_SHOVEL = ITEMS.registerItem("dark_crystal_shovel",
            properties -> new DarkCrystalShovel(ToolTiers.DARK_CRYSTAL_TIER, 1, -3, properties.stacksTo(1)));
    public static final DeferredHolder<Item, PickaxeItem> PAINITE_PICKAXE = ITEMS.registerItem("painite_pickaxe",
            properties -> new PainitePickaxe(ToolTiers.PAINITE_TIER, 1, -2.8f, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, AxeItem> PAINITE_AXE = ITEMS.registerItem("painite_axe",
            properties -> new PainiteAxe(ToolTiers.PAINITE_TIER, 9, -3.3f, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, SwordItem> PAINITE_SWORD = ITEMS.registerItem("painite_sword",
            properties -> new PainiteSword(ToolTiers.PAINITE_TIER, 7, -2.4f, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, HoeItem> PAINITE_HOE = ITEMS.registerItem("painite_scythe",
            properties -> new PainiteScythe(ToolTiers.PAINITE_TIER, 3, 0, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, ShovelItem> PAINITE_SHOVEL = ITEMS.registerItem("painite_shovel",
            properties -> new PainiteShovel(ToolTiers.PAINITE_TIER, 1, -3, properties.fireResistant().stacksTo(1)));
    public static final DeferredHolder<Item, PickaxeItem> COPPERWOOD_PICKAXE = ITEMS.registerItem("copperwood_pickaxe",
            properties -> new CopperwoodPickaxe(ToolTiers.COPPERWOOD_TIER, 1, -2.8f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, AxeItem> COPPERWOOD_AXE = ITEMS.registerItem("copperwood_axe",
            properties -> new CopperwoodAxe(ToolTiers.COPPERWOOD_TIER, 9, -3.0f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, SwordItem> COPPERWOOD_SWORD = ITEMS.registerItem("copperwood_sword",
            properties -> new CopperwoodSword(ToolTiers.COPPERWOOD_TIER, 4, -1.8f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, HoeItem> COPPERWOOD_HOE = ITEMS.registerItem("copperwood_hoe",
            properties -> new CopperwoodHoe(ToolTiers.COPPERWOOD_TIER, 0, 0, properties.stacksTo(1)));
    public static final DeferredHolder<Item, ShovelItem> COPPERWOOD_SHOVEL = ITEMS.registerItem("copperwood_shovel",
            properties -> new CopperwoodShovel(ToolTiers.COPPERWOOD_TIER, 1, -3, properties.stacksTo(1)));
    public static final DeferredHolder<Item, PickaxeItem> MAGNETIC_PICKAXE = ITEMS.registerItem("magnetic_pickaxe",
            properties -> new MagneticPickaxe(ToolTiers.MAGNETIC_TIER, 1, -2.8f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, AxeItem> MAGNETIC_AXE = ITEMS.registerItem("magnetic_axe",
            properties -> new MagneticAxe(ToolTiers.MAGNETIC_TIER, 9, -3.0f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, SwordItem> MAGNETIC_SWORD = ITEMS.registerItem("magnetic_sword",
            properties -> new MagneticSword(ToolTiers.MAGNETIC_TIER, 6, -2.4f, properties.stacksTo(1)));
    public static final DeferredHolder<Item, HoeItem> MAGNETIC_HOE = ITEMS.registerItem("magnetic_hoe",
            properties -> new MagneticHoe(ToolTiers.MAGNETIC_TIER, 0, 0, properties.stacksTo(1)));
    public static final DeferredHolder<Item, ShovelItem> MAGNETIC_SHOVEL = ITEMS.registerItem("magnetic_shovel",
            properties -> new MagneticShovel(ToolTiers.MAGNETIC_TIER, 1, -3, properties.stacksTo(1)));

    public static final Supplier<BlockItem> CHARRED_BARK = ITEMS.registerSimpleBlockItem(BlockRegistry.CHARRED_BARK);
    public static final Supplier<BlockItem> CHARRED_PLANKS = ITEMS.registerSimpleBlockItem(BlockRegistry.CHARRED_PLANKS);
    public static final DeferredHolder<Item, BucketItem> COOL_LAVA_BUCKET = ITEMS.registerItem("cerulean_lava_bucket",
            props -> new BucketItem(FluidRegistry.COOL_LAVA.get(), props), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET));
    public static final Supplier<BlockItem> DEAD_DANDELION = ITEMS.registerSimpleBlockItem(BlockRegistry.DEAD_DANDELION);
    public static final Supplier<BlockItem> DEAD_POPPY = ITEMS.registerSimpleBlockItem(BlockRegistry.DEAD_POPPY);
    public static final Supplier<BlockItem> DEAD_GRASS = ITEMS.registerSimpleBlockItem(BlockRegistry.DEAD_GRASS);
    public static final Supplier<BlockItem> PAINITE_ORE_SOIL = ITEMS.registerSimpleBlockItem(BlockRegistry.PAINITE_ORE);
    public static final Supplier<BlockItem> STRIPPED_CHARRED_BARK = ITEMS.registerSimpleBlockItem(BlockRegistry.STRIPPED_CHARRED_BARK);
    public static final Supplier<BlockItem> VOLCANIC_ROCK = ITEMS.registerSimpleBlockItem(BlockRegistry.VOLCANIC_ROCK);
    public static final Supplier<BlockItem> VOLCANIC_SOIL = ITEMS.registerSimpleBlockItem(BlockRegistry.VOLCANIC_SOIL);
    public static final Supplier<BlockItem> FROZEN_SAND = ITEMS.registerSimpleBlockItem(BlockRegistry.FROZEN_SAND);
    public static final Supplier<BlockItem> FROZEN_CACTUS = ITEMS.registerSimpleBlockItem(BlockRegistry.FROZEN_CACTUS);
    public static final Supplier<BlockItem> DEEPSLATE_DARK_CRYSTAL_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.DEEPSLATE_DARK_CRYSTAL_ORE);
    public static final Supplier<BlockItem> DARK_CRYSTAL_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.DARK_CRYSTAL_ORE);
    public static final Supplier<BlockItem> STRIPPED_ELECTREE_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.STRIPPED_ELECTREE_LOG);
    public static final Supplier<BlockItem> ELECTREE_SAPLING = ITEMS.registerSimpleBlockItem(BlockRegistry.ELECTREE_SAPLING);
    public static final Supplier<BlockItem> BLUE_PASTELIZED_SAPLING = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_PASTELIZED_SAPLING);
    public static final Supplier<BlockItem> RED_PASTELIZED_SAPLING = ITEMS.registerSimpleBlockItem(BlockRegistry.RED_PASTELIZED_SAPLING);
    public static final Supplier<BlockItem> ORANGE_BULB = ITEMS.registerSimpleBlockItem(BlockRegistry.ORANGE_BULB);
    public static final Supplier<BlockItem> GREEN_ROSE_BULB = ITEMS.registerSimpleBlockItem(BlockRegistry.GREEN_ROSE_BULB);
    public static final Supplier<BlockItem> ELECTREE_PLANKS = ITEMS.registerSimpleBlockItem(BlockRegistry.ELECTREE_PLANKS);
    public static final Supplier<BlockItem> ELECTREE_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.ELECTREE_LOG);
    public static final Supplier<BlockItem> ELECTREE_LEAVES = ITEMS.registerSimpleBlockItem(BlockRegistry.ELECTREE_LEAVES);
    public static final Supplier<BlockItem> BLUE_BULB = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_BULB);
    public static final Supplier<BlockItem> BLUE_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> BLUE_PASTEL_LEAVES = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_PASTEL_LEAVES);
    public static final Supplier<BlockItem> BLUE_PASTEL_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_PASTEL_LOG);
    public static final Supplier<BlockItem> BLUE_PASTEL_PETALS = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_PASTEL_PETALS);
    public static final Supplier<BlockItem> BLUE_PASTEL_PLANKS = ITEMS.registerSimpleBlockItem(BlockRegistry.BLUE_PASTEL_PLANKS);
    public static final Supplier<BlockItem> COPPERWOOD_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.COPPERWOOD_ORE);
    public static final Supplier<BlockItem> MAGNETIC_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.MAGNETIC_ORE);
    public static final Supplier<BlockItem> CYAN_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.CYAN_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> DEEPSLATE_COPPERWOOD_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.DEEPSLATE_COPPERWOOD_ORE);
    public static final Supplier<BlockItem> DEEPSLATE_MAGNETIC_ORE = ITEMS.registerSimpleBlockItem(BlockRegistry.DEEPSLATE_MAGNETIC_ORE);
    public static final Supplier<BlockItem> GREEN_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.GREEN_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> LIME_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.LIME_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> MAGENTA_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.MAGENTA_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> MOSSY_DIRT = ITEMS.registerSimpleBlockItem(BlockRegistry.MOSSY_DIRT);
    public static final Supplier<BlockItem> MOSSY_GRASS = ITEMS.registerSimpleBlockItem(BlockRegistry.MOSSY_GRASS);
    public static final Supplier<BlockItem> LIGHTNING_DIRT = ITEMS.registerSimpleBlockItem(BlockRegistry.LIGHTNING_DIRT);
    public static final Supplier<BlockItem> LIGHTNING_MARSH = ITEMS.registerSimpleBlockItem(BlockRegistry.LIGHTNING_MARSH);
    public static final Supplier<BlockItem> ORANGE_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.ORANGE_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> PINK_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.PINK_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> PURPLE_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.PURPLE_COLORFUL_FLOWER);
    public static final Supplier<BlockItem> RED_PASTEL_LEAVES = ITEMS.registerSimpleBlockItem(BlockRegistry.RED_PASTEL_LEAVES);
    public static final Supplier<BlockItem> RED_PASTEL_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.RED_PASTEL_LOG);
    public static final Supplier<BlockItem> RED_PASTEL_PETALS = ITEMS.registerSimpleBlockItem(BlockRegistry.RED_PASTEL_PETALS);
    public static final Supplier<BlockItem> RED_PASTEL_PLANKS = ITEMS.registerSimpleBlockItem(BlockRegistry.RED_PASTEL_PLANKS);
    public static final Supplier<BlockItem> ROCKS = ITEMS.registerSimpleBlockItem(BlockRegistry.ROCKS);
    public static final Supplier<BlockItem> STRIPPED_BLUE_PASTEL_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG);
    public static final Supplier<BlockItem> STRIPPED_RED_PASTEL_LOG = ITEMS.registerSimpleBlockItem(BlockRegistry.STRIPPED_RED_PASTEL_LOG);
    public static final Supplier<BlockItem> YELLOW_COLORFUL_FLOWER = ITEMS.registerSimpleBlockItem(BlockRegistry.YELLOW_COLORFUL_FLOWER);

    public static final Supplier<Item> CERATOSAURUS_SPAWN_EGG = ITEMS.registerItem("ceratosaurus_spawn_egg",
            props -> new DeferredSpawnEggItem(EntityRegistry.CERATOSAURUS, 0xFFFFFF, 0, props));

    private static DeferredHolder<Item, ArmorItem> registerArmorWithDescription(String name, Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        return ITEMS.register(name, () -> new ArmorItem(material, type, properties) {
            @Override
            public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                pTooltipComponents.add(Component.translatable("description." + AlternaCraft.MODID + "." + name).withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
                super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            }
        });
    }
}
