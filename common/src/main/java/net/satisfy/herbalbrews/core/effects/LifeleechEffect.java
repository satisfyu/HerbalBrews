package net.satisfy.herbalbrews.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import java.util.List;

public class LifeleechEffect extends MobEffect {
    private static final int RADIUS = 10;
    private static final int TRANSFER_INTERVAL = 160;

    public LifeleechEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && player.isAlive()) {
            Level world = player.level();
            List<Player> nearbyPlayers = world.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(RADIUS), this::isAffectedEntity);
            int playerCount = nearbyPlayers.size() - 1;
            playerCount = Math.max(playerCount, 0);
            int finalAmplifier = calculateFinalAmplifier(playerCount);
            if (finalAmplifier > 0) {
                nearbyPlayers.stream()
                        .filter(p -> p.isAlive() && p != player && !p.isCreative())
                        .forEach(p -> applyEffect(p, finalAmplifier));
            }
            if (isTransferTime(entity)) {
                transferHealth(player);
            }
        }
    }

    private int calculateFinalAmplifier(int playerCount) {
        float baseAmplifier = 1.0f;
        float reductionPerPlayer = 0.1f;
        float calculatedAmplifier = baseAmplifier - (playerCount * reductionPerPlayer);
        calculatedAmplifier = Math.max(calculatedAmplifier, 0);
        return Math.min(Math.round(calculatedAmplifier), 3);
    }

    private void applyEffect(Player player, int amplifier) {
        player.addEffect(new MobEffectInstance(this, 200, amplifier, false, false, false));
    }

    private boolean isAffectedEntity(Player player) {
        return player.isAlive() && !player.isCreative();
    }

    private boolean isTransferTime(LivingEntity entity) {
        return entity.tickCount % LifeleechEffect.TRANSFER_INTERVAL == 0;
    }

    private void transferHealth(Player player) {
        Level world = player.level();
        List<LivingEntity> enemies = world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(LifeleechEffect.RADIUS), this::isEnemy);
        for (LivingEntity enemy : enemies) {
            if (enemy.getHealth() > 0) {
                enemy.setHealth(Math.max(enemy.getHealth() - 1.0F, 0));
                player.setHealth(Math.min(player.getHealth() + 1.0F, player.getMaxHealth()));
            }
        }
    }

    private boolean isEnemy(LivingEntity entity) {
        return entity instanceof Monster;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
