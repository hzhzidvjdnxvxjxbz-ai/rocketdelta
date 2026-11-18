package com.rocket.delta.module;

import com.rocket.delta.event.impl.PacketEvent;
import com.rocket.delta.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected static final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final Category category;
    private int key = -1;
    private boolean enabled;
    private final List<Setting> settings = new ArrayList<>();

    protected Module(String name, Category category) { this.name = name; this.category = category; }

    protected void addSettings(Setting... s) { settings.addAll(List.of(s)); }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public boolean isEnabled() { return enabled; }
    public List<Setting> getSettings() { return settings; }

    public void toggle() { setEnabled(!enabled); }
    public void setEnabled(boolean value) {
        if (value == enabled) return;
        enabled = value;
        if (value) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender2D(DrawContext context) {}
    public void onPacket(PacketEvent event) {}
}
