package io.github.itskillerluc.alternacraft.util;

import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

import java.util.Map;

public interface EntityHeadMap {
    Map<LivingEntity, PartDefinition> getEntityHeadMap();
}
