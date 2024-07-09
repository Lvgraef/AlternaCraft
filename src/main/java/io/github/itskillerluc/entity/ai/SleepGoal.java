package io.github.itskillerluc.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SleepGoal<T extends LivingEntity & Sleeping> extends Goal {

    private final int coolDown;
    private final T entity;
    private final int randomChance;

    private int timer = 0;

    public SleepGoal(T entity, int randomChance, int coolDown) {
        this.entity = entity;
        this.randomChance = randomChance;
        this.coolDown = coolDown;
    }

    @Override
    public boolean canUse() {
        return timer-- <= 0;
    }

    @Override
    public void tick() {
        if (entity.getRandom().nextInt(randomChance) == 0) {
            timer = coolDown;
            entity.setSleeping(entity.getSleepingPattern().shouldSleep(entity.level().getDayTime()));
            stop();
        }
    }
}
