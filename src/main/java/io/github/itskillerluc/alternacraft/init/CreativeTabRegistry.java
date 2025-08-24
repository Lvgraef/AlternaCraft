package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;
@SuppressWarnings("unused")
public class CreativeTabRegistry {
    private static final List<Supplier<? extends Item>> EXCLUDED = List.of(
            ItemRegistry.COPPERWOOD_SWORD,
            ItemRegistry.PAINITE_SWORD,
            ItemRegistry.AIO_SWORD
    );
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, AlternaCraft.MODID);

    public static final Supplier<CreativeModeTab> ALTERNACRAFT_TAB = CREATIVE_TABS.register("alternacraft_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("ítemGroup." + AlternaCraft.MODID + ".main"))
                    .icon(() -> new ItemStack(ItemRegistry.AIO_CRYSTAL.get()))
                    .displayItems((params, output) -> {
                        for (DeferredHolder<Item, ? extends Item> entry : ItemRegistry.ITEMS.getEntries()) {
                            if (EXCLUDED.contains(entry)) {
                                if (entry.get() == ItemRegistry.COPPERWOOD_SWORD.get()) {
                                    output.accept(Util.make(() -> {
                                        var stack = new ItemStack(ItemRegistry.COPPERWOOD_SWORD.get());
                                        stack.enchant(params.holders().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE), 5);
                                        return stack;
                                    }));
                                } else if (entry.get() == ItemRegistry.PAINITE_SWORD.get()) {
                                    output.accept(Util.make(() -> {
                                        var stack = new ItemStack(ItemRegistry.PAINITE_SWORD.get());
                                        stack.enchant(params.holders().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT), 5);
                                        return stack;
                                    }));
                                }
                                else if (entry.get() == ItemRegistry.AIO_SWORD.get()) {
                                    output.accept(Util.make(() -> {
                                        var stack = new ItemStack(ItemRegistry.AIO_SWORD.get());
                                        stack.enchant(params.holders().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT), 5);
                                        stack.enchant(params.holders().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE), 5);
                                        return stack;
                                    }));
                                }
                            } else {
                                output.accept(entry.get());
                            }
                        }
                    })
                    .build());
}
