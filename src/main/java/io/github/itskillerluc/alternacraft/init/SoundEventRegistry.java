package io.github.itskillerluc.alternacraft.init;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AlternaCraft.MODID);

    public static final Supplier<SoundEvent> SONAR_PING = SOUND_EVENTS.register("sonar_ping",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "sonar_ping")));
    public static final Supplier<SoundEvent> SONAR_PONG = SOUND_EVENTS.register("sonar_pong",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "sonar_pong")));
}
