package com.rocket.delta.util;

import net.minecraft.client.gui.DrawContext;

public class RenderUtil {
    /** simple rect with cut corners (cheap rounded look) */
    public static void roundedRect(DrawContext c, int x1, int y1, int x2, int y2, int color) {
        c.fill(x1 + 1, y1, x2 - 1, y2, color);
        c.fill(x1, y1 + 1, x2, y2 - 1, color);
    }

    public static void line(DrawContext c, int x1, int y1, int x2, int y2, int color) {
        c.fill(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2) + 1, Math.max(y1, y2) + 1, color);
    }

    // TODO: 3D-боксы для ESP (через Render3DEvent)
}
