package com.rocket.delta.gui;

import com.rocket.delta.RocketDeltaClient;
import com.rocket.delta.gui.component.CategoryPanel;
import com.rocket.delta.gui.component.ModuleButton;
import com.rocket.delta.module.Category;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private final List<CategoryPanel> panels = new ArrayList<>();

    public ClickGuiScreen() { super(Text.literal("RocketDelta")); }

    @Override
    protected void init() {
        panels.clear();
        int x = 10;
        for (Category c : Category.values()) {
            panels.add(new CategoryPanel(c, RocketDeltaClient.INSTANCE.moduleManager.getByCategory(c), x, 10));
            x += CategoryPanel.W + 8;
        }
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0x66000000);
        for (CategoryPanel p : panels) p.render(ctx, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        for (CategoryPanel p : panels) if (p.click(mx, my, button)) return true;
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int button) {
        for (CategoryPanel p : panels) p.release();
        return super.mouseReleased(mx, my, button);
    }

    @Override
    public boolean keyPressed(int key, int scan, int mods) {
        if (ModuleButton.listening != null) { ModuleButton.listening.onKey(key); return true; }
        if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT) { close(); return true; }
        return super.keyPressed(key, scan, mods);
    }

    @Override public boolean shouldPause() { return false; }
    @Override public void removed() { RocketDeltaClient.INSTANCE.configManager.save(); }
}
