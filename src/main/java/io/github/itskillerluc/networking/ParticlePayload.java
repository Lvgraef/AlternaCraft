package io.github.itskillerluc.networking;

import io.github.itskillerluc.AlternaCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.joml.Vector2f;
import org.joml.Vector3f;

public record ParticlePayload(Vector3f direction, ParticleOptions particleOptions, Vector3f position, float spread, int count) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ParticlePayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AlternaCraft.MODID, "particle"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ParticlePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F,
            ParticlePayload::direction,
            ParticleTypes.STREAM_CODEC,
            ParticlePayload::particleOptions,
            ByteBufCodecs.VECTOR3F,
            ParticlePayload::position,
            ByteBufCodecs.FLOAT,
            ParticlePayload::spread,
            ByteBufCodecs.INT,
            ParticlePayload::count,
            ParticlePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleData(final ParticlePayload data, final IPayloadContext context) {
        var random = Minecraft.getInstance().level.random;

        Vector3f direction = data.direction();

        for (int i = 0; i < data.count(); i++) {
            double randomAngle = Math.toRadians(random.nextInt(0, 360));
            Vector2f offset = new Vector2f(
                    (float) (Math.sqrt(random.nextInt(0, (int) (data.spread * 100)) / 100d) * Math.cos(randomAngle)),
                    (float) (Math.sqrt(random.nextInt(0, (int) (data.spread * 100)) / 100d) * Math.sin(randomAngle)));
            Vector3f xOffset = new Vector3f(direction).cross(new Vector3f(0, 1, 0)).normalize().mul(offset.x);
            Vector3f yOffset = new Vector3f(direction).cross(xOffset).normalize().mul(offset.y);
            Vector3f dirVec = new Vector3f(direction).add(xOffset).add(yOffset).normalize();

            Minecraft.getInstance().level.addParticle(
                    data.particleOptions(),
                    data.position().x,
                    data.position().y,
                    data.position().z,
                    dirVec.x,
                    dirVec.y,
                    dirVec.z
            );
        }
    }
}
