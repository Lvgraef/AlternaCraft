package io.github.itskillerluc.datagen;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
    }
    private static final List<Supplier<? extends Item>> EXCLUDED = List.of(
            ItemRegistry.AIO_BOOTS,
            ItemRegistry.AIO_CHESTPLATE,
            ItemRegistry.AIO_HELMET,
            ItemRegistry.AIO_LEGGINGS,
            ItemRegistry.COPPERWOOD_BOOTS,
            ItemRegistry.COPPERWOOD_CHESTPLATE,
            ItemRegistry.COPPERWOOD_HELMET,
            ItemRegistry.COPPERWOOD_LEGGINGS,
            ItemRegistry.DARK_CRYSTAL_BOOTS,
            ItemRegistry.DARK_CRYSTAL_CHESTPLATE,
            ItemRegistry.DARK_CRYSTAL_HELMET,
            ItemRegistry.DARK_CRYSTAL_LEGGINGS,
            ItemRegistry.MAGNETIC_BOOTS,
            ItemRegistry.MAGNETIC_CHESTPLATE,
            ItemRegistry.MAGNETIC_HELMET,
            ItemRegistry.MAGNETIC_LEGGINGS,
            ItemRegistry.PAINITE_BOOTS,
            ItemRegistry.PAINITE_CHESTPLATE,
            ItemRegistry.PAINITE_HELMET,
            ItemRegistry.PAINITE_LEGGINGS
    );

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AlternaCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> entry : ItemRegistry.ITEMS.getEntries()) {
            if (EXCLUDED.contains(entry) || entry.get() instanceof BlockItem) continue;
            basicItem(entry.get());
        }
        basicItem(ItemRegistry.BLUE_PASTELIZED_SAPLING.get());
        basicItem(ItemRegistry.RED_PASTELIZED_SAPLING.get());
        basicItem(ItemRegistry.ELECTREE_SAPLING.get());

        withExistingParent("alternacraft:aio_axe", "minecraft:item/handheld");
        withExistingParent("alternacraft:aio_pickaxe", "minecraft:item/handheld");
        withExistingParent("alternacraft:aio_scythe", "minecraft:item/handheld");
        withExistingParent("alternacraft:aio_shovel", "minecraft:item/handheld");
        withExistingParent("alternacraft:aio_sword", "minecraft:item/handheld");
        withExistingParent("alternacraft:copperwood_axe", "minecraft:item/handheld");
        withExistingParent("alternacraft:copperwood_hoe", "minecraft:item/handheld");
        withExistingParent("alternacraft:copperwood_pickaxe", "minecraft:item/handheld");
        withExistingParent("alternacraft:copperwood_shovel", "minecraft:item/handheld");
        withExistingParent("alternacraft:copperwood_sword", "minecraft:item/handheld");
        withExistingParent("alternacraft:dark_crystal_axe", "minecraft:item/handheld");
        withExistingParent("alternacraft:dark_crystal_hoe", "minecraft:item/handheld");
        withExistingParent("alternacraft:dark_crystal_pickaxe", "minecraft:item/handheld");
        withExistingParent("alternacraft:dark_crystal_shovel", "minecraft:item/handheld");
        withExistingParent("alternacraft:dark_crystal_sword", "minecraft:item/handheld");
        withExistingParent("alternacraft:magnetic_axe", "minecraft:item/handheld");
        withExistingParent("alternacraft:magnetic_hoe", "minecraft:item/handheld");
        withExistingParent("alternacraft:magnetic_pickaxe", "minecraft:item/handheld");
        withExistingParent("alternacraft:magnetic_shovel", "minecraft:item/handheld");
        withExistingParent("alternacraft:magnetic_sword", "minecraft:item/handheld");
        withExistingParent("alternacraft:painite_axe", "minecraft:item/handheld");
        withExistingParent("alternacraft:painite_pickaxe", "minecraft:item/handheld");
        withExistingParent("alternacraft:painite_scythe", "minecraft:item/handheld");
        withExistingParent("alternacraft:painite_shovel", "minecraft:item/handheld");
        withExistingParent("alternacraft:painite_sword", "minecraft:item/handheld");

        trimmedArmorItem(ItemRegistry.AIO_BOOTS);
        trimmedArmorItem(ItemRegistry.AIO_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.AIO_HELMET);
        trimmedArmorItem(ItemRegistry.AIO_LEGGINGS);
        trimmedArmorItem(ItemRegistry.COPPERWOOD_BOOTS);
        trimmedArmorItem(ItemRegistry.COPPERWOOD_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.COPPERWOOD_HELMET);
        trimmedArmorItem(ItemRegistry.COPPERWOOD_LEGGINGS);
        trimmedArmorItem(ItemRegistry.DARK_CRYSTAL_BOOTS);
        trimmedArmorItem(ItemRegistry.DARK_CRYSTAL_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.DARK_CRYSTAL_HELMET);
        trimmedArmorItem(ItemRegistry.DARK_CRYSTAL_LEGGINGS);
        trimmedArmorItem(ItemRegistry.MAGNETIC_BOOTS);
        trimmedArmorItem(ItemRegistry.MAGNETIC_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.MAGNETIC_HELMET);
        trimmedArmorItem(ItemRegistry.MAGNETIC_LEGGINGS);
        trimmedArmorItem(ItemRegistry.PAINITE_BOOTS);
        trimmedArmorItem(ItemRegistry.PAINITE_CHESTPLATE);
        trimmedArmorItem(ItemRegistry.PAINITE_HELMET);
        trimmedArmorItem(ItemRegistry.PAINITE_LEGGINGS);
    }

    private void trimmedArmorItem(DeferredHolder<Item, ? extends Item> item) {
        final String MOD_ID = AlternaCraft.MODID;

        if(item.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + item.getId().getPath();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.withDefaultNamespace(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(item.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(ResourceLocation.withDefaultNamespace("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + item.getId().getPath()));
            });
        }
    }}
