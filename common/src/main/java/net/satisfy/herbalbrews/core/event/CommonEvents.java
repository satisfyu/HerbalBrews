package net.satisfy.herbalbrews.core.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.satisfy.herbalbrews.core.items.HatItem;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import org.jetbrains.annotations.Nullable;

public class CommonEvents {
    public static void init() {
        PlayerEvent.ATTACK_ENTITY.register(CommonEvents::attack);
        EntityEvent.LIVING_HURT.register(CommonEvents::onLivingHurt);
    }

    public static EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        if (player.getItemInHand(hand).is(ObjectRegistry.JUG_ITEM.get()) && target instanceof LivingEntity) {
            target.hurt(level.damageSources().generic(), 10.0F);
            level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
            level.addParticle(ParticleTypes.CRIT, target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ(), 0, 0, 0);
            if (!player.isCreative()) {
                player.getItemInHand(hand).shrink(1);
            }
            return EventResult.interruptTrue();
        }
        return EventResult.pass();
    }

    public static EventResult onLivingHurt(LivingEntity entity, DamageSource source, float amount) {
        if (entity instanceof Player) {
            boolean isMagic = source == entity.level().damageSources().magic();
            HatItem.applyMagicResistance(entity, amount, isMagic);
        }
        return EventResult.pass();
    }
}
