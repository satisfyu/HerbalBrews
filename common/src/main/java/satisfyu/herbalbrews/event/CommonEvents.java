package satisfyu.herbalbrews.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class CommonEvents {
    public static void init() {
        PlayerEvent.ATTACK_ENTITY.register(CommonEvents::attack);
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
}
