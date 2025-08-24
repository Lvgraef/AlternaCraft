package io.github.itskillerluc.alternacraft.init;

import com.mojang.serialization.Codec;
import io.github.itskillerluc.alternacraft.AlternaCraft;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentTypeRegistry {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, AlternaCraft.MODID);

    public static final Supplier<AttachmentType<Boolean>> STUNNED = ATTACHMENT_TYPES.register("stunned",
            () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
}
