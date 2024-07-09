package io.github.itskillerluc.entity.ai;

import com.mojang.datafixers.util.Pair;

import java.util.List;

public enum SleepingPattern {
    NOCTURNAL(List.of(new Pair<>(0, 12))),
    DIURNAL(List.of(new Pair<>(13, 23))),
    CREPUSCULAR(List.of(new Pair<>(23, 24), new Pair<>(12, 13))),
    CATHEMERAL(List.of(new Pair<>(2, 4), new Pair<>(7, 8), new Pair<>(11, 13), new Pair<>(15, 16), new Pair<>(19, 21), new Pair<>(23, 24)));

    private final List<Pair<Integer, Integer>> sleepingHours;

    SleepingPattern(List<Pair<Integer, Integer>> sleepingHours) {
        this.sleepingHours = sleepingHours;
    }

    public boolean shouldSleep(long ticks) {
        for (Pair<Integer, Integer> sleepingHour : sleepingHours) {
            if (sleepingHour.getFirst() <= ticks / 1000 && ticks / 1000 <= sleepingHour.getSecond()) {
                return true;
            }
        }
        return false;
    }
}
