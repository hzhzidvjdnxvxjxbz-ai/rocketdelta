package com.rocket.delta.util;

public class MathUtil {
    public static double lerp(double a, double b, double t) { return a + (b - a) * t; }
    public static double round(double v, int places) {
        double m = Math.pow(10, places);
        return Math.round(v * m) / m;
    }

    public static class Timer {
        private long last = System.currentTimeMillis();
        public boolean passed(long ms) { return System.currentTimeMillis() - last >= ms; }
        public void reset() { last = System.currentTimeMillis(); }
    }
}
