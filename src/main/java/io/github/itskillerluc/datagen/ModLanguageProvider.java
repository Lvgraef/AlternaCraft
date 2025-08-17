package io.github.itskillerluc.datagen;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.BlockRegistry;
import io.github.itskillerluc.init.EffectRegistry;
import io.github.itskillerluc.init.EntityRegistry;
import io.github.itskillerluc.init.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    private static final List<Supplier<? extends Item>> EXCLUDED_ITEMS = List.of(
            ItemRegistry.AIO_AXE,
            ItemRegistry.AIO_BOOTS,
            ItemRegistry.AIO_CHESTPLATE,
            ItemRegistry.AIO_CRYSTAL,
            ItemRegistry.AIO_HELMET,
            ItemRegistry.AIO_HOE,
            ItemRegistry.AIO_LEGGINGS,
            ItemRegistry.AIO_PICKAXE,
            ItemRegistry.AIO_SHOVEL,
            ItemRegistry.AIO_SWORD
    );

    private static final List<Supplier<? extends Block>> EXCLUDED_BLOCKS = List.of(
    );

    private static final List<Supplier<? extends EntityType<?>>> EXCLUDED_ENTITIES = List.of(
    );

    public ModLanguageProvider(PackOutput output) {
        super(output, AlternaCraft.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Creative mode tabs
        add("ítemGroup." + AlternaCraft.MODID + ".main", "AlternaCraft");

        // Items
        for (DeferredHolder<Item, ? extends Item> entry : ItemRegistry.ITEMS.getEntries()) {
            if (EXCLUDED_ITEMS.contains(entry)) continue;
            var input = entry.getKey().location().getPath();
            var translation = getName(input);
            if (entry.get() instanceof BlockItem) {
                add("item." + AlternaCraft.MODID + "." + entry.getId().getPath(), translation);
            } else {
                addItem(entry, translation);
            }
        }

        addItem(ItemRegistry.AIO_AXE, "All In One Axe");
        addItem(ItemRegistry.AIO_BOOTS, "All In One Boots");
        addItem(ItemRegistry.AIO_CHESTPLATE, "All In One Chestplate");
        addItem(ItemRegistry.AIO_CRYSTAL, "All In One Crystal");
        addItem(ItemRegistry.AIO_HELMET, "All In One Helmet");
        addItem(ItemRegistry.AIO_HOE, "All In One Hoe");
        addItem(ItemRegistry.AIO_LEGGINGS, "All In One Leggings");
        addItem(ItemRegistry.AIO_PICKAXE, "All In One Pickaxe");
        addItem(ItemRegistry.AIO_SHOVEL, "All In One Shovel");
        addItem(ItemRegistry.AIO_SWORD, "All In One Sword");

        // Item descriptions
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_AXE.getId().getPath(), "Auto-Smelts!\nPuts drops directly in inventory.\nPoisons your enemies.\nBlinds your enemies and slows them down.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_BOOTS.getId().getPath(), "Prevents fall damage and grants strength 2, regeneration 2 and fire resistance when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_CHESTPLATE.getId().getPath(), "Prevents fall damage and grants strength 2, regeneration 2 and fire resistance when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_HELMET.getId().getPath(), "Prevents fall damage and grants strength 2, regeneration 2 and fire resistance when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_HOE.getId().getPath(), "Auto-Smelts!\nPuts drops directly in inventory.\nAutomatically replants harvested crops (rightclick).\nTills a 3x3");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_LEGGINGS.getId().getPath(), "Prevents fall damage and grants strength 2, regeneration 2 and fire resistance when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_PICKAXE.getId().getPath(), "Auto-Smelts!\nPuts drops directly in inventory.\nRightclick to put down mining lights.\nShift Rightclick with an ore in your offhand to find the distance of the ore using sonar.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_SHOVEL.getId().getPath(), "Auto-Smelts!\nPuts drops directly in inventory.\nGives xp for every block mined.\nMines a 3x3.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.AIO_SWORD.getId().getPath(), "Sets the enemy on fire.\nPuts drops directly in inventory.\nPoisons your enemies with an extra large swing.\nBlinds your enemies.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_AXE.getId().getPath(), "Poisons your enemies.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_BOOTS.getId().getPath(), "Grants regeneration 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_CHESTPLATE.getId().getPath(), "Grants regeneration 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_HELMET.getId().getPath(), "Grants regeneration 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_HOE.getId().getPath(), "Automatically replants harvested crops (rightclick).");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_LEGGINGS.getId().getPath(), "Grants regeneration 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_PICKAXE.getId().getPath(), "Rightclick to put down mining lights.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_SHOVEL.getId().getPath(), "Gives xp for every block mined.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.COPPERWOOD_SWORD.getId().getPath(), "Poisons your enemies with an extra large swing.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_AXE.getId().getPath(), "Blinds your enemies and slows them down.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_BOOTS.getId().getPath(), "Grants strength 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_CHESTPLATE.getId().getPath(), "Grants strength 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_HELMET.getId().getPath(), "Grants strength 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_HOE.getId().getPath(), "Tills a 3x3");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_LEGGINGS.getId().getPath(), "Grants strength 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_PICKAXE.getId().getPath(), "Rightclick with an ore in your offhand to find the distance of the ore using sonar.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_SHOVEL.getId().getPath(), "Mines a 3x3.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.DARK_CRYSTAL_SWORD.getId().getPath(), "Blinds your enemies.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_AXE.getId().getPath(), "Puts drops directly in inventory.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_BOOTS.getId().getPath(), "Prevents fall damage when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_CHESTPLATE.getId().getPath(), "Prevents fall damage when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_HELMET.getId().getPath(), "Prevents fall damage when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_HOE.getId().getPath(), "Puts drops directly in inventory.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_LEGGINGS.getId().getPath(), "Prevents fall damage when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_PICKAXE.getId().getPath(), "Puts drops directly in inventory.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_SHOVEL.getId().getPath(), "Puts drops directly in inventory.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.MAGNETIC_SWORD.getId().getPath(), "Puts drops directly in inventory.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_AXE.getId().getPath(), "Auto-Smelts!");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_BOOTS.getId().getPath(), "Grants fire resistance 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_CHESTPLATE.getId().getPath(), "Grants fire resistance 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_HELMET.getId().getPath(), "Grants fire resistance 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_HOE.getId().getPath(), "Auto-Smelts!");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_LEGGINGS.getId().getPath(), "Grants fire resistance 1 when full set is worn.");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_PICKAXE.getId().getPath(), "Auto-Smelts!");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_SHOVEL.getId().getPath(), "Auto-Smelts!");
        add("description." + AlternaCraft.MODID + "." + ItemRegistry.PAINITE_SWORD.getId().getPath(), "Sets the enemy on fire.");


        //Subtitles
        add("subtitle.alternacraft.sonar_ping", "Sonar Ping");
        add("subtitle.alternacraft.sonar_pong", "Sonar Pong");

        //Blocks
        for (DeferredHolder<Block, ? extends Block> entry : BlockRegistry.BLOCKS.getEntries()) {
            if (EXCLUDED_BLOCKS.contains(entry)) continue;
            var input = entry.getKey().location().getPath();
            addBlock(entry, getName(input));
        }

        //Entities
        for (DeferredHolder<EntityType<?>, ? extends EntityType<?>> entry : EntityRegistry.ENTITY_TYPES.getEntries()) {
            if (EXCLUDED_ENTITIES.contains(entry)) continue;
            var input = entry.getKey().location().getPath();
            addEntityType(entry, getName(input));
        }

        //Painting
        add("painting.alternacraft.luc_when_coding.title", "Luc When Coding");
        add("painting.alternacraft.luc_when_coding.author", "Cylixr");

        //Effects
        for (DeferredHolder<MobEffect, ? extends MobEffect> entry : EffectRegistry.EFFECTS.getEntries()) {
            var input = entry.getKey().location().getPath();
            addEffect(entry, getName(input));
        }
    }

    private static String getName(String input) {
        return Arrays.stream(input.split("_"))
                .map(word -> Character.toTitleCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
