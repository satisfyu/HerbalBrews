package net.satisfy.herbalbrews.core.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.effects.*;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;

import java.util.function.Supplier;

public class EffectRegistry {

    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.MOB_EFFECT);
    private static final Registrar<MobEffect> MOB_EFFECTS_REGISTRAR = MOB_EFFECTS.getRegistrar();

    public static final RegistrySupplier<MobEffect> DEEPRUSH;
    public static final RegistrySupplier<MobEffect> BONDING;
    public static final RegistrySupplier<MobEffect> FORTUNE;

    public static final RegistrySupplier<MobEffect> BALANCED;
    public static final RegistrySupplier<MobEffect> FERAL;
    public static final RegistrySupplier<MobEffect> EXCAVATION;
    public static final RegistrySupplier<MobEffect> RENEWAL;
    public static final RegistrySupplier<MobEffect> TOUGH;


    private static RegistrySupplier<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        if (Platform.isForge()) {
            return MOB_EFFECTS.register(name, effect);
        }
        return MOB_EFFECTS_REGISTRAR.register(new HerbalBrewsIdentifier(name), effect);
    }

    public static void init() {
        MOB_EFFECTS.register();
    }

    static {
        //Updated Effects
        DEEPRUSH = registerEffect("deeprush", DeeprushEffect::new);
        BONDING = registerEffect("bonding", BondingEffect::new);
        FORTUNE = registerEffect("fortune", FortuneEffect::new);


        RENEWAL = registerEffect("renewal", BondingEffect::new);
        BALANCED = registerEffect("balanced", BalancedEffect::new);
        FERAL = registerEffect("feral", FeralEffect::new);
        EXCAVATION = registerEffect("excavation", ExcavationEffect::new);
        TOUGH = registerEffect("tough", ToughEffect::new);
    }
}

