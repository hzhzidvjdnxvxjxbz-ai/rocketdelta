package com.rocket.delta.event.impl;

import com.rocket.delta.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent extends Event {
    public final MatrixStack matrices;
    public final float tickDelta;
    public Render3DEvent(MatrixStack matrices, float tickDelta) { this.matrices = matrices; this.tickDelta = tickDelta; }
}
