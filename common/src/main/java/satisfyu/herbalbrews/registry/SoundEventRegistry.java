package satisfyu.herbalbrews.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class SoundEventRegistry {

    private static final Registrar<SoundEvent> SOUND_EVENTS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.SOUND_EVENT).getRegistrar();
    public static final RegistrySupplier<SoundEvent> KETTLE_BOILING = create("kettle_boiling");


    private static RegistrySupplier<SoundEvent> create(String name) {
        final ResourceLocation id = new HerbalBrewsIdentifier(name);
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {
        HerbalBrews.LOGGER.debug("Registering Sounds for " + HerbalBrews.MOD_ID);
    }
}
