package com.rocket.delta.gui.component;

import com.rocket.delta.module.Module;
import com.rocket.delta.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    public static final int H = 14;
    public static ModuleButton listening;

    public final Module module;
    private final List<SettingComponent> settings = new ArrayList<>();
    private boolean expanded;
    private int x, y, w;

    public ModuleButton(Module module) {
        this.module = module;
        for (Setting s : module.getSettings()) settings.add(new SettingComponent(s));
    }

    /** returns total height used */
    public int render(DrawContext c, int x, int y, int w) {
        this.x = x; this.y = y; this.w = w;
        MinecraftClient mc = MinecraftClient.getInstance();
        c.fill(x, y, x + w, y + H, module.isEnabled() ? 0xFFCC3311 : 0xFF202020);
        String label = listening == this ? module.getName() + " [...]" : module.getName();
        c.drawText(mc.textRenderer, label, x + 4, y + 3, 0xFFFFFFFF, true);
        int total = H;
        if (expanded) {
            for (SettingComponent s : settings) {
                s.render(c, x, y + total, w);
                total += SettingComponent.H;
            }
        }
        return total;
    }

    public boolean click(double mx, double my, int button) {
        if (mx >= x && mx <= x + w && my >= y && my <= y + H) {
            if (button == 0) module.toggle();
            else if (button == 1) expanded = !expanded;
            else if (button == 2) listening = this;
            return true;
        }
        if (expanded) for (SettingComponent s : settings) if (s.click(mx, my, button)) return true;
        return false;
    }

    public void onKey(int key) {
        module.setKey(key == GLFW.GLFW_KEY_ESCAPE ? -1 : key);
        listening = null;
    }
}
