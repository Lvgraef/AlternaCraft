package io.github.itskillerluc.alternacraft.entity.ai;

public interface Sleeping {
    SleepingPattern getSleepingPattern();
    boolean isSleeping();
    void setSleeping(boolean sleeping);
}
