package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TreeGrowers {
    public static final TreeGrower ELECTREE = new TreeGrower(AlternaCraft.MODID + ":electree",
            Optional.empty(), Optional.of(ConfiguredFeatureInit.ELECTREE), Optional.empty());

    public static final TreeGrower BLUE_PASTELIZED_TREE = new TreeGrower(AlternaCraft.MODID + "blue_pastelized_tree",
            Optional.empty(), Optional.of(ConfiguredFeatureInit.BLUE_PASTELIZED_TREE), Optional.empty());

    public static final TreeGrower RED_PASTELIZED_TREE = new TreeGrower(AlternaCraft.MODID + "red_pastelized_tree",
            Optional.empty(), Optional.of(ConfiguredFeatureInit.RED_PASTELIZED_TREE), Optional.empty());
}
