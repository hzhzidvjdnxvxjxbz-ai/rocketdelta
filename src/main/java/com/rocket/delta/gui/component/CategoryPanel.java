package com.rocket.delta.gui.component;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel {
    public static final int W = 100, HEADER = 16;
    private final Category category;
    private final List<ModuleButton> buttons = new ArrayList<>();
    private int x, y;
    private boolean dragging;
    private int dragX, dragY;

    public CategoryPanel(Category category, List<Module> modules, int x, int y) {
        this.category = category; this.x = x; this.y = y;
        for (Module m : modules) buttons.add(new ModuleButton(m));
    }

    public void render(DrawContext c, int mouseX, int mouseY) {
        if (dragging) { x = mouseX - dragX; y = mouseY - dragY; }
        MinecraftClient mc = MinecraftClient.getInstance();
        c.fill(x, y, x + W, y + HEADER, 0xFFFF5522);
        c.drawText(mc.textRenderer, category.label, x + 4, y + 4, 0xFFFFFFFF, true);
        int cy = y + HEADER;
        for (ModuleButton b : buttons) cy += b.render(c, x, cy, W);
    }

    public boolean click(double mx, double my, int button) {
        if (mx >= x && mx <= x + W && my >= y && my <= y + HEADER && button == 0) {
            dragging = true; dragX = (int) mx - x; dragY = (int) my - y;
            return true;
        }
        for (ModuleButton b : buttons) if (b.click(mx, my, button)) return true;
        return false;
    }

    public void release() { dragging = false; }
}
