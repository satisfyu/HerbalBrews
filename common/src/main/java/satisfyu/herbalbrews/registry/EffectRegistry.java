package satisfyu.herbalbrews.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.effects.*;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

import java.util.function.Supplier;

public class EffectRegistry {

    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.MOB_EFFECT);
    private static final Registrar<MobEffect> MOB_EFFECTS_REGISTRAR = MOB_EFFECTS.getRegistrar();

    public static final RegistrySupplier<MobEffect> BALANCED;
    public static final RegistrySupplier<MobEffect> FERAL;
    public static final RegistrySupplier<MobEffect> FORTUNE;
    public static final RegistrySupplier<MobEffect> LASTSTAND;
    public static final RegistrySupplier<MobEffect> POISONOUSBREATH;
    public static final RegistrySupplier<MobEffect> RENEWAL;
    public static final RegistrySupplier<MobEffect> REVITALIZING;
    public static final RegistrySupplier<MobEffect> TOUGH;


    private static RegistrySupplier<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        if (Platform.isForge()) {
            return MOB_EFFECTS.register(name, effect);
        }
        return MOB_EFFECTS_REGISTRAR.register(new HerbalBrewsIdentifier(name), effect);
    }

    public static void init() {
        HerbalBrews.LOGGER.debug("Mob effects");
        MOB_EFFECTS.register();
    }

    static {
        RENEWAL = registerEffect("renewal", RenewalEffect::new);
        BALANCED = registerEffect("balanced", BalancedEffect::new);
        FORTUNE = registerEffect("fortune", FortuneEffect::new);
        FERAL = registerEffect("feral", FeralEffect::new);
        LASTSTAND = registerEffect("laststand", LastStandEffect::new);
        TOUGH = registerEffect("tough", ToughEffect::new);
        REVITALIZING = registerEffect("revitalizing", RevitalizingEffect::new);
        POISONOUSBREATH = registerEffect("poisonous", PoisonousbreathEffect::new);
    }
}

