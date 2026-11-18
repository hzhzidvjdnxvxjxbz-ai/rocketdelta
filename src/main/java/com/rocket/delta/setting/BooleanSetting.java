package com.rocket.delta.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BooleanSetting extends Setting {
    private boolean value;
    public BooleanSetting(String name, boolean value) { super(name); this.value = value; }
    public boolean get() { return value; }
    public void set(boolean v) { value = v; }
    public void toggle() { value = !value; }
    @Override public JsonElement save() { return new JsonPrimitive(value); }
    @Override public void load(JsonElement e) { value = e.getAsBoolean(); }
}
