package com.rocket.delta.gui.component;

import com.rocket.delta.setting.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class SettingComponent {
    public static final int H = 12;
    private final Setting setting;
    private int x, y, w;

    public SettingComponent(Setting setting) { this.setting = setting; }

    public void render(DrawContext c, int x, int y, int w) {
        this.x = x; this.y = y; this.w = w;
        MinecraftClient mc = MinecraftClient.getInstance();
        c.fill(x, y, x + w, y + H, 0xCC151515);
        String text = setting.getName();
        if (setting instanceof BooleanSetting b) text += ": " + (b.get() ? "ON" : "OFF");
        else if (setting instanceof ModeSetting m) text += ": " + m.get();
        else if (setting instanceof NumberSetting n) {
            text += ": " + n.get();
            double pct = (n.get() - n.getMin()) / (n.getMax() - n.getMin());
            c.fill(x, y + H - 2, x + (int) (w * pct), y + H, 0xFFFF5522);
        }
        c.drawText(mc.textRenderer, text, x + 3, y + 2, 0xFFFFFFFF, true);
    }

    public boolean click(double mx, double my, int button) {
        if (mx < x || mx > x + w || my < y || my > y + H) return false;
        if (setting instanceof BooleanSetting b && button == 0) b.toggle();
        else if (setting instanceof ModeSetting m && button == 0) m.cycle();
        else if (setting instanceof NumberSetting n && button == 0) {
            double pct = (mx - x) / w;
            n.set(n.getMin() + pct * (n.getMax() - n.getMin()));
        }
        return true;
    }
}
