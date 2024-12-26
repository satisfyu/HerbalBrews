package net.satisfy.herbalbrews.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;

public class SoundEventRegistry {

    private static final Registrar<SoundEvent> SOUND_EVENTS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.SOUND_EVENT).getRegistrar();

    public static final RegistrySupplier<SoundEvent> TEA_KETTLE_BOILING = create("tea_kettle_boiling");

    public static void init() {}

    private static RegistrySupplier<SoundEvent> create(String name) {
        ResourceLocation id = new HerbalBrewsIdentifier(name);
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
