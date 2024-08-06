package io.github.itskillerluc.init;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class TreeGrowers {
    public static final TreeGrower ELECTREE = new TreeGrower(AlternaCraft.MODID + ":electree",
            Optional.empty(), Optional.of(ConfiguredFeatureInit.ELECTREE), Optional.empty());
}
