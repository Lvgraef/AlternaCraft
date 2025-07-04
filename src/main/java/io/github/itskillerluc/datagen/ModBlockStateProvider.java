package io.github.itskillerluc.datagen;

import io.github.itskillerluc.AlternaCraft;
import io.github.itskillerluc.init.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final List<Supplier<? extends Block>> EXCLUDED = List.of(
            BlockRegistry.MINING_LIGHT,
            BlockRegistry.CHARRED_BARK,
            BlockRegistry.STRIPPED_CHARRED_BARK,
            BlockRegistry.DEAD_DANDELION,
            BlockRegistry.DEAD_GRASS,
            BlockRegistry.DEAD_POPPY,
            BlockRegistry.COOL_LAVA,
            BlockRegistry.FROZEN_CACTUS,
            BlockRegistry.ELECTREE_LOG,
            BlockRegistry.STRIPPED_ELECTREE_LOG,
            BlockRegistry.BLUE_BULB,
            BlockRegistry.ELECTREE_LEAVES,
            BlockRegistry.ORANGE_BULB,
            BlockRegistry.GREEN_ROSE_BULB,
            BlockRegistry.BLUE_COLORFUL_FLOWER,
            BlockRegistry.BLUE_PASTEL_LEAVES,
            BlockRegistry.BLUE_PASTEL_LOG,
            BlockRegistry.RED_PASTEL_LOG,
            BlockRegistry.GREEN_COLORFUL_FLOWER,
            BlockRegistry.LIME_COLORFUL_FLOWER,
            BlockRegistry.MAGENTA_COLORFUL_FLOWER,
            BlockRegistry.MOSSY_GRASS,
            BlockRegistry.LIGHTNING_MARSH,
            BlockRegistry.ORANGE_COLORFUL_FLOWER,
            BlockRegistry.PINK_COLORFUL_FLOWER,
            BlockRegistry.PURPLE_COLORFUL_FLOWER,
            BlockRegistry.RED_PASTEL_LEAVES,
            BlockRegistry.ROCKS,
            BlockRegistry.STRIPPED_BLUE_PASTEL_LOG,
            BlockRegistry.STRIPPED_RED_PASTEL_LOG,
            BlockRegistry.YELLOW_COLORFUL_FLOWER,
            BlockRegistry.BLUE_PASTEL_PETALS,
            BlockRegistry.RED_PASTEL_PETALS,
            BlockRegistry.CYAN_COLORFUL_FLOWER,
            BlockRegistry.MOSSY_DIRT,
            BlockRegistry.LIGHTNING_DIRT,
            BlockRegistry.BLUE_PASTELIZED_SAPLING,
            BlockRegistry.RED_PASTELIZED_SAPLING,
            BlockRegistry.ELECTREE_SAPLING
    );

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AlternaCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredHolder<Block, ? extends Block> entry : BlockRegistry.BLOCKS.getEntries()) {
            if (EXCLUDED.contains(entry)) continue;
            simpleBlockWithItem(entry.get(), cubeAll(entry.get()));
        }
        simpleBlockWithItem(BlockRegistry.BLUE_PASTEL_LEAVES.get(), models().cubeAll("blue_pastel_leaves", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/blue_pastel_leaves")).renderType("minecraft:cutout"));
        simpleBlockWithItem(BlockRegistry.ELECTREE_LEAVES.get(), models().cubeAll("electree_leaves", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/electree_leaves")).renderType("minecraft:cutout"));
        simpleBlockWithItem(BlockRegistry.RED_PASTEL_LEAVES.get(), models().cubeAll("red_pastel_leaves", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/red_pastel_leaves")).renderType("minecraft:cutout"));

        petals("blue_pastel_petals", BlockRegistry.BLUE_PASTEL_PETALS.get());
        petals("red_pastel_petals", BlockRegistry.RED_PASTEL_PETALS.get());
        simpleBlockItem(BlockRegistry.BLUE_PASTEL_PETALS.get(), itemModels().basicItem(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "blue_pastel_petals_item")));
        simpleBlockItem(BlockRegistry.RED_PASTEL_PETALS.get(), itemModels().basicItem(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "red_pastel_petals_item")));

        BlockModelBuilder mossyDirtModel = models().cubeAll("mossy_dirt", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/mossy_dirt"));
        BlockModelBuilder mossyGrassModel = models().cubeBottomTop("mossy_grass", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/mossy_grass_side"), ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/mossy_dirt"), ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/mossy_grass"));
        BlockModelBuilder lightningDirtModel = models().cubeAll("lightning_dirt", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/lightning_dirt"));
        BlockModelBuilder lightningMarshModel = models().cubeBottomTop("lightning_marsh", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/lightning_marsh"), ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/lightning_dirt"), ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/lightning_marsh_top"));

        getVariantBuilder(BlockRegistry.MOSSY_GRASS.value())
                .partialState().modelForState()
                .modelFile(mossyGrassModel).rotationY(0).nextModel()
                .modelFile(mossyGrassModel).rotationY(90).nextModel()
                .modelFile(mossyGrassModel).rotationY(180).nextModel()
                .modelFile(mossyGrassModel).rotationY(270).addModel();
        simpleBlockItem(BlockRegistry.MOSSY_GRASS.get(), mossyGrassModel);


        getVariantBuilder(BlockRegistry.MOSSY_DIRT.value())
                .partialState().modelForState()
                .modelFile(mossyDirtModel).rotationY(0).nextModel()
                .modelFile(mossyDirtModel).rotationY(90).nextModel()
                .modelFile(mossyDirtModel).rotationY(180).nextModel()
                .modelFile(mossyDirtModel).rotationY(270).addModel();
        simpleBlockItem(BlockRegistry.MOSSY_DIRT.get(), mossyDirtModel);

        getVariantBuilder(BlockRegistry.LIGHTNING_MARSH.value())
                .partialState().modelForState()
                .modelFile(lightningMarshModel).rotationY(0).nextModel()
                .modelFile(lightningMarshModel).rotationY(90).nextModel()
                .modelFile(lightningMarshModel).rotationY(180).nextModel()
                .modelFile(lightningMarshModel).rotationY(270).addModel();
        simpleBlockItem(BlockRegistry.LIGHTNING_MARSH.get(), lightningMarshModel);


        getVariantBuilder(BlockRegistry.LIGHTNING_DIRT.value())
                .partialState().modelForState()
                .modelFile(lightningDirtModel).rotationY(0).nextModel()
                .modelFile(lightningDirtModel).rotationY(90).nextModel()
                .modelFile(lightningDirtModel).rotationY(180).nextModel()
                .modelFile(lightningDirtModel).rotationY(270).addModel();
        simpleBlockItem(BlockRegistry.LIGHTNING_DIRT.get(), lightningDirtModel);


        BlockModelBuilder miningLight = models().withExistingParent("mining_light","minecraft:block/air");

        logBlock(BlockRegistry.BLUE_PASTEL_LOG.value());
        logBlock(BlockRegistry.CHARRED_BARK.value());
        logBlock(BlockRegistry.ELECTREE_LOG.value());
        logBlock(BlockRegistry.RED_PASTEL_LOG.value());
        logBlock(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG.value());
        logBlock(BlockRegistry.STRIPPED_CHARRED_BARK.value());
        logBlock(BlockRegistry.STRIPPED_ELECTREE_LOG.value());
        logBlock(BlockRegistry.STRIPPED_RED_PASTEL_LOG.value());
        simpleBlock(BlockRegistry.MINING_LIGHT.get(), miningLight);
        simpleBlockItem(BlockRegistry.BLUE_PASTEL_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/blue_pastel_log")));
        simpleBlockItem(BlockRegistry.CHARRED_BARK.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/charred_bark")));
        simpleBlockItem(BlockRegistry.ELECTREE_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/electree_log")));
        simpleBlockItem(BlockRegistry.RED_PASTEL_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/red_pastel_log")));
        simpleBlockItem(BlockRegistry.STRIPPED_BLUE_PASTEL_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/stripped_blue_pastel_log")));
        simpleBlockItem(BlockRegistry.STRIPPED_CHARRED_BARK.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/stripped_charred_bark")));
        simpleBlockItem(BlockRegistry.STRIPPED_ELECTREE_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/stripped_electree_log")));
        simpleBlockItem(BlockRegistry.STRIPPED_RED_PASTEL_LOG.value(), itemModels().getExistingFile(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/stripped_red_pastel_log")));

        itemModels().basicItem(BlockRegistry.BLUE_BULB.getId());
        itemModels().basicItem(BlockRegistry.BLUE_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.CYAN_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.DEAD_DANDELION.getId());
        itemModels().basicItem(BlockRegistry.DEAD_GRASS.getId());
        itemModels().basicItem(BlockRegistry.DEAD_POPPY.getId());
        itemModels().basicItem(BlockRegistry.GREEN_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.GREEN_ROSE_BULB.getId());
        itemModels().basicItem(BlockRegistry.LIME_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.MAGENTA_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.ORANGE_BULB.getId());
        itemModels().basicItem(BlockRegistry.ORANGE_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.PINK_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.PURPLE_COLORFUL_FLOWER.getId());
        itemModels().basicItem(BlockRegistry.YELLOW_COLORFUL_FLOWER.getId());
        simpleBlock(BlockRegistry.BLUE_BULB.get(), models().cross("blue_bulb", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/blue_bulb")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.BLUE_COLORFUL_FLOWER.get(), models().cross("blue_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/blue_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.CYAN_COLORFUL_FLOWER.get(), models().cross("cyan_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/cyan_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.DEAD_DANDELION.get(), models().cross("dead_dandelion", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/dead_dandelion")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.DEAD_GRASS.get(), models().cross("dead_grass", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/dead_grass")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.DEAD_POPPY.get(), models().cross("dead_poppy", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/dead_poppy")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.GREEN_COLORFUL_FLOWER.get(), models().cross("green_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/green_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.GREEN_ROSE_BULB.get(), models().cross("green_rose_bulb", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/green_rose_bulb")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.LIME_COLORFUL_FLOWER.get(), models().cross("lime_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/lime_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.MAGENTA_COLORFUL_FLOWER.get(), models().cross("magenta_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/magenta_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.ORANGE_BULB.get(), models().cross("orange_bulb", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/orange_bulb")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.ORANGE_COLORFUL_FLOWER.get(), models().cross("orange_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/orange_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.PINK_COLORFUL_FLOWER.get(), models().cross("pink_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/pink_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.PURPLE_COLORFUL_FLOWER.get(), models().cross("purple_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/purple_colorful_flower")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.YELLOW_COLORFUL_FLOWER.get(), models().cross("yellow_colorful_flower", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/yellow_colorful_flower")).renderType("minecraft:cutout"));

        BlockModelBuilder frozenCactus = models().withExistingParent("frozen_cactus", "minecraft:block/cactus")
                .texture("particle", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/frozen_cactus_side"))
                .texture("bottom", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/frozen_cactus_bottom"))
                .texture("side", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/frozen_cactus_side"))
                .texture("top", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/frozen_cactus_top"))
                .renderType("minecraft:cutout");
        simpleBlockWithItem(BlockRegistry.FROZEN_CACTUS.get(), frozenCactus);

        BlockModelBuilder rocks_0 = models().getBuilder(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_0").toString())
                        .element().from(0, 0, 0).to(16, 0.1f, 16).face(Direction.UP).texture("#rock").end()
                        .from(0, 0, 0).to(16, 0.1f, 16).face(Direction.DOWN).uvs(0, 16, 16, 0).texture("#rock").end().end().texture("particle", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_0")).texture("rock", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_0")).renderType("minecraft:cutout");
        BlockModelBuilder rocks_1 = models().getBuilder(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_1").toString())
                .element().from(0, 0, 0).to(16, 0.1f, 16).face(Direction.UP).texture("#rock").end()
                .from(0, 0, 0).to(16, 0.1f, 16).face(Direction.DOWN).uvs(0, 16, 16, 0).texture("#rock").end().end().texture("particle", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_1")).texture("rock", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_1")).renderType("minecraft:cutout");
        BlockModelBuilder rocks_2 = models().getBuilder(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_2").toString())
                .element().from(0, 0, 0).to(16, 0.1f, 16).face(Direction.UP).texture("#rock").end()
                .from(0, 0, 0).to(16, 0.1f, 16).face(Direction.DOWN).uvs(0, 16, 16, 0).texture("#rock").end().end().texture("particle", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_2")).texture("rock", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/rocks_2")).renderType("minecraft:cutout");


        getVariantBuilder(BlockRegistry.ROCKS.value())
                .forAllStates(state ->
                                ArrayUtils.addAll(ArrayUtils.addAll(
                                        ConfiguredModel.allYRotations(rocks_0, 0, false),
                                        ConfiguredModel.allYRotations(rocks_1, 0, false)),
                                        ConfiguredModel.allYRotations(rocks_2, 0, false)));

        simpleBlockItem(BlockRegistry.ROCKS.value(), itemModels().basicItem(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "rocks_item")));
        simpleBlock(BlockRegistry.COOL_LAVA.value(), models().getBuilder(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava").toString()).texture("particle", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/cool_lava_still")));
        simpleBlock(BlockRegistry.BLUE_PASTELIZED_SAPLING.get(), models().cross("blue_pastelized_sapling", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/blue_pastelized_sapling")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.RED_PASTELIZED_SAPLING.get(), models().cross("red_pastelized_sapling", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/red_pastelized_sapling")).renderType("minecraft:cutout"));
        simpleBlock(BlockRegistry.ELECTREE_SAPLING.get(), models().cross("electree_sapling", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "item/electree_sapling")).renderType("minecraft:cutout"));

    }

    private void petals(String name, Block block) {
        getMultipartBuilder(block)
                .part().rotationY(0).modelFile(models().withExistingParent(name + "_1", mcLoc("block/flowerbed_1")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.NORTH)
                    .condition(PinkPetalsBlock.AMOUNT, 1, 2, 3, 4).end()
                .part().rotationY(90).modelFile(models().withExistingParent(name + "_1", mcLoc("block/flowerbed_1")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.EAST)
                    .condition(PinkPetalsBlock.AMOUNT, 1, 2, 3, 4).end()
                .part().rotationY(180).modelFile(models().withExistingParent(name + "_1", mcLoc("block/flowerbed_1")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.SOUTH)
                    .condition(PinkPetalsBlock.AMOUNT, 1, 2, 3, 4).end()
                .part().rotationY(270).modelFile(models().withExistingParent(name + "_1", mcLoc("block/flowerbed_1")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.WEST)
                    .condition(PinkPetalsBlock.AMOUNT, 1, 2, 3, 4).end()
                .part().rotationY(0).modelFile(models().withExistingParent(name + "_2", mcLoc("block/flowerbed_2")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.NORTH)
                    .condition(PinkPetalsBlock.AMOUNT, 2, 3, 4).end()
                .part().rotationY(90).modelFile(models().withExistingParent(name + "_2", mcLoc("block/flowerbed_2")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.EAST)
                    .condition(PinkPetalsBlock.AMOUNT, 2, 3, 4).end()
                .part().rotationY(180).modelFile(models().withExistingParent(name + "_2", mcLoc("block/flowerbed_2")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.SOUTH)
                    .condition(PinkPetalsBlock.AMOUNT, 2, 3, 4).end()
                .part().rotationY(270).modelFile(models().withExistingParent(name + "_2", mcLoc("block/flowerbed_2")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.WEST)
                    .condition(PinkPetalsBlock.AMOUNT, 2, 3, 4).end()
                .part().rotationY(0).modelFile(models().withExistingParent(name + "_3", mcLoc("block/flowerbed_3")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.NORTH)
                    .condition(PinkPetalsBlock.AMOUNT, 3, 4).end()
                .part().rotationY(90).modelFile(models().withExistingParent(name + "_3", mcLoc("block/flowerbed_3")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.EAST)
                    .condition(PinkPetalsBlock.AMOUNT, 3, 4).end()
                .part().rotationY(180).modelFile(models().withExistingParent(name + "_3", mcLoc("block/flowerbed_3")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.SOUTH)
                    .condition(PinkPetalsBlock.AMOUNT, 3, 4).end()
                .part().rotationY(270).modelFile(models().withExistingParent(name + "_3", mcLoc("block/flowerbed_3")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.WEST)
                    .condition(PinkPetalsBlock.AMOUNT, 3, 4).end()
                .part().rotationY(0).modelFile(models().withExistingParent(name + "_4", mcLoc("block/flowerbed_4")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.NORTH)
                    .condition(PinkPetalsBlock.AMOUNT, 4).end()
                .part().rotationY(90).modelFile(models().withExistingParent(name + "_4", mcLoc("block/flowerbed_4")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.EAST)
                    .condition(PinkPetalsBlock.AMOUNT, 4).end()
                .part().rotationY(180).modelFile(models().withExistingParent(name + "_4", mcLoc("block/flowerbed_4")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.SOUTH)
                    .condition(PinkPetalsBlock.AMOUNT, 4).end()
                .part().rotationY(270).modelFile(models().withExistingParent(name + "_4", mcLoc("block/flowerbed_4")).renderType("cutout").texture("flowerbed", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name)).texture("stem", ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "block/" + name + "_stem"))).addModel()
                    .condition(PinkPetalsBlock.FACING, Direction.WEST)
                    .condition(PinkPetalsBlock.AMOUNT, 4).end();
    }
}