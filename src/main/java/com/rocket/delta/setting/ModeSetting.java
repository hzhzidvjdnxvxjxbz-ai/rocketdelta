package com.rocket.delta.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {
    private final List<String> modes;
    private int index;
    public ModeSetting(String name, String def, String... modes) {
        super(name);
        this.modes = Arrays.asList(modes);
        this.index = Math.max(0, this.modes.indexOf(def));
    }
    public String get() { return modes.get(index); }
    public boolean is(String mode) { return get().equalsIgnoreCase(mode); }
    public void cycle() { index = (index + 1) % modes.size(); }
    @Override public JsonElement save() { return new JsonPrimitive(get()); }
    @Override public void load(JsonElement e) {
        int i = modes.indexOf(e.getAsString());
        if (i >= 0) index = i;
    }
}
