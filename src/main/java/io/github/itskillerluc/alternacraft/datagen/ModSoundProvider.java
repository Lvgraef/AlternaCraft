package io.github.itskillerluc.alternacraft.datagen;

import io.github.itskillerluc.alternacraft.AlternaCraft;
import io.github.itskillerluc.alternacraft.init.SoundEventRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundProvider extends SoundDefinitionsProvider {
    protected ModSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, AlternaCraft.MODID, helper);
    }

    @Override
    public void registerSounds() {
        add(SoundEventRegistry.SONAR_PING, SoundDefinition.definition()
                .with(sound("alternacraft:sonar_ping"))
                .subtitle("subtitle.alternacraft.sonar_ping"));
        add(SoundEventRegistry.SONAR_PONG, SoundDefinition.definition()
                .with(sound("alternacraft:sonar_pong"))
                .subtitle("subtitle.alternacraft.sonar_pong"));
    }
}
