package com.rocket.delta.setting;

import com.google.gson.JsonElement;

public abstract class Setting {
    private final String name;
    protected Setting(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract JsonElement save();
    public abstract void load(JsonElement element);
}
