package satisfyu.herbalbrews.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;

public class RedWolfEntity extends Wolf {
    public RedWolfEntity(EntityType<? extends Wolf> entityType, Level world) {
        super(entityType, world);
    }
}