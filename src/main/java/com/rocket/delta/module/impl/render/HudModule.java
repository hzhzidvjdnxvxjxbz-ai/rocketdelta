package com.rocket.delta.module.impl.render;

import com.rocket.delta.RocketDeltaClient;
import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import net.minecraft.client.gui.DrawContext;
import java.util.Comparator;
import java.util.List;

public class HudModule extends Module {
    public HudModule() { super("HUD", Category.RENDER); setEnabled(true); }

    @Override
    public void onRender2D(DrawContext ctx) {
        if (mc.player == null) return;
        ctx.drawText(mc.textRenderer, "RocketDelta", 4, 4, 0xFFFF5522, true);
        ctx.drawText(mc.textRenderer, String.format("XYZ: %.1f %.1f %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ()), 4, 16, 0xFFFFFFFF, true);

        List<Module> active = RocketDeltaClient.INSTANCE.moduleManager.getAll().stream()
            .filter(m -> m.isEnabled() && m != this)
            .sorted(Comparator.comparingInt((Module m) -> mc.textRenderer.getWidth(m.getName())).reversed())
            .toList();
        int y = 4;
        int w = ctx.getScaledWindowWidth();
        for (Module m : active) {
            int tw = mc.textRenderer.getWidth(m.getName());
            ctx.drawText(mc.textRenderer, m.getName(), w - tw - 4, y, 0xFFFFFFFF, true);
            y += 10;
        }
    }
}
