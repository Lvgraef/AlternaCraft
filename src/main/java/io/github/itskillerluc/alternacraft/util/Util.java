package io.github.itskillerluc.alternacraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforgespi.language.ModFileScanData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static final Map<Class<?>, List<PartDefinition>> HEAD_MAP = new HashMap<>();

    public static double closestToZero(double first, double second) {
        return Math.abs(first) < Math.abs(second) ? first : second;
    }

    public static void initializeHeadMap(Map<ModelLayerLocation, LayerDefinition> roots) {
        FMLLoader.getLoadingModList().getMods()
                .parallelStream()
                .flatMap(mod -> mod.getOwningFile().getFile().getScanResult().getClasses()
                        .parallelStream()
                        .map(ModFileScanData.ClassData::clazz))
                .filter(data -> data.getClassName().toLowerCase().contains("entity") &&
                        !data.getClassName().toLowerCase().contains("client") &&
                        !data.getClassName().toLowerCase().contains("model") &&
                        !data.getClassName().toLowerCase().contains("renderer"))
                .map(data -> {
                    try {
                        return Class.forName(data.getClassName());
                    } catch (ClassNotFoundException | NoClassDefFoundError e) {
                        return null;
                    }
                })
                .filter(data -> data != null && LivingEntity.class.isAssignableFrom(data))
                .forEach(elem -> HEAD_MAP.put(elem,
                                roots.entrySet()
                                        .stream()
                                        .map(entry -> Map.entry(entry.getKey().getModel().getPath(), entry.getValue()))
                                        .filter(entry -> elem.getName().toLowerCase().contains(entry.getKey()))
                                        .findFirst().map(optional -> {
                                            PartDefinition root = optional.getValue().mesh.getRoot();
                                            PartDefinition head = null;

                                            var paths = new String[][]{{"head"}, {"Head"}, {"body", "head"}, {"body", "Head"}, {"Body", "head"}, {"Body", "Head"}, {"root", "head"},
                                                    {"root", "Head"}, {"root", "body", "head"}, {"root", "body", "Head"}, {"root", "Body", "head"}, {"root", "Body", "Head"},
                                                    {"Root", "head"}, {"Root", "Head"}, {"Root", "body", "head"}, {"Root", "body", "Head"}, {"Root", "Body", "head"},
                                                    {"Root", "Body", "Head"}};

                                            List<PartDefinition> children = new ArrayList<>();

                                            for (String[] path : paths) {
                                                if (root.getChild(path[0]) != null) {
                                                    children.add(root.getChild(path[0]));
                                                    if (path.length > 1) {
                                                        if (root.getChild(path[0]).getChild(path[1]) != null) {
                                                            children.add(root.getChild(path[0]).getChild(path[1]));
                                                            if (path.length > 2) {
                                                                if (root.getChild(path[0]).getChild(path[1]).getChild(path[2]) != null) {
                                                                    children.add(root.getChild(path[0]).getChild(path[1]).getChild(path[2]));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            return children;
                                        }).orElse(null)
                        )
                );
    }
}
