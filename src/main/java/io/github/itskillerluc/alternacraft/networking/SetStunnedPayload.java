package io.github.itskillerluc.alternacraft.networking;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.init.AttachmentTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record SetStunnedPayload(int entityId, boolean stunned) implements CustomPacketPayload {
    public static final Type<SetStunnedPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "set_stunned"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SetStunnedPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SetStunnedPayload::entityId,
            ByteBufCodecs.BOOL,
            SetStunnedPayload::stunned,
            SetStunnedPayload::new
    );

    public static void handleData(final SetStunnedPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();

            ClientLevel level = minecraft.level;
            if (level == null) return;

            Entity entity = Minecraft.getInstance().level.getEntity(data.entityId);
            if (entity == null) return;
            entity.setData(AttachmentTypeRegistry.STUNNED, data.stunned);
        });
    }

    @Override
    public @NotNull Type<SetStunnedPayload> type() {
        return TYPE;
    }
}
