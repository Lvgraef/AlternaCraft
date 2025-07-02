package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ArmorMaterialRegistry {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, AlternaCraft.MODID);

    public static final Holder<ArmorMaterial> COPPERWOOD = ARMOR_MATERIALS.register("copperwood", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ItemRegistry.COPPERWOOD_CRYSTAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "copperwood"))),
            2.0F,
            0.0F
    ));//37

    public static final Holder<ArmorMaterial> DARK_CRYSTAL = ARMOR_MATERIALS.register("dark_crystal", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ItemRegistry.DARK_CRYSTAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "dark_crystal"))),
            3.0F,
            0.1F
    ));//39

    public static final Holder<ArmorMaterial> MAGNET = ARMOR_MATERIALS.register("magnet", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ItemRegistry.MAGNETIC_CRYSTAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "magnet"))),
            3.0F,
            0.1F
    ));//40

    public static final Holder<ArmorMaterial> PAINITE = ARMOR_MATERIALS.register("painite", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ItemRegistry.PAINITE_CRYSTAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "painite"))),
            4.0F,
            0.2F
    ));//44

    public static final Holder<ArmorMaterial> AIO = ARMOR_MATERIALS.register("aio", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }),
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(ItemRegistry.AIO_CRYSTAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "aio"))),
            6.0F,
            0.5F
    ));//60
}
