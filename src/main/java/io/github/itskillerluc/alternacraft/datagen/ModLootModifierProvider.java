package io.github.itskillerluc.alternacraft.datagen;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.init.ItemRegistry;
import io.github.itskillerluc.alternacraft.loot.SmeltingLootModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AlternaCraft.MODID);
    }

    @Override
    protected void start() {
        add("painite_tool_modifier", new SmeltingLootModifier(new LootItemCondition[] {
                MatchTool.toolMatches(
                        ItemPredicate.Builder.item().of(
                                ItemRegistry.AIO_AXE.get(),
                                ItemRegistry.AIO_HOE.get(),
                                ItemRegistry.AIO_PICKAXE.get(),
                                ItemRegistry.AIO_SHOVEL.get(),
                                ItemRegistry.PAINITE_AXE.get(),
                                ItemRegistry.PAINITE_HOE.get(),
                                ItemRegistry.PAINITE_PICKAXE.get(),
                                ItemRegistry.PAINITE_SHOVEL.get())).build()
        }));
    }
}
