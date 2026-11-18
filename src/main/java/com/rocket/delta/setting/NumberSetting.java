package com.rocket.delta.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class NumberSetting extends Setting {
    private double value;
    private final double min, max, step;
    public NumberSetting(String name, double value, double min, double max, double step) {
        super(name); this.value = value; this.min = min; this.max = max; this.step = step;
    }
    public double get() { return value; }
    public float getFloat() { return (float) value; }
    public int getInt() { return (int) Math.round(value); }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public void set(double v) {
        v = Math.max(min, Math.min(max, v));
        value = Math.round(v / step) * step;
    }
    @Override public JsonElement save() { return new JsonPrimitive(value); }
    @Override public void load(JsonElement e) { set(e.getAsDouble()); }
}
