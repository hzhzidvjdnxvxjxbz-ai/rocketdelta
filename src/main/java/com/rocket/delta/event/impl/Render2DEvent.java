package com.rocket.delta.event.impl;

import com.rocket.delta.event.Event;
import net.minecraft.client.gui.DrawContext;

public class Render2DEvent extends Event {
    public final DrawContext context;
    public final float tickDelta;
    public Render2DEvent(DrawContext context, float tickDelta) { this.context = context; this.tickDelta = tickDelta; }
}
