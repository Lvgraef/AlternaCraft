package io.github.itskillerluc.util;

public class Util {
    public static double closestToZero(double first, double second) {
        return Math.abs(first) < Math.abs(second) ? first : second;
    }
}
