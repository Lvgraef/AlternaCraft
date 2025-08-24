package io.github.itskillerluc.alternacraft.networking;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SoundPayload(String sound) implements CustomPacketPayload {
    public static final Type<SoundPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "sound"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SoundPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SoundPayload::sound,
            SoundPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleData(final SoundPayload data, final IPayloadContext context) {
        var sound = BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(data.sound()));
        if (sound == null) {
            throw new IllegalArgumentException("Sound not found: " + data.sound());
        }
        context.player().playSound(sound);
    }
}
