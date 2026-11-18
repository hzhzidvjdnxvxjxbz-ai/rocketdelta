package com.rocket.delta.module.impl.combat;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class VelocityModule extends Module {
    public VelocityModule() { super("Velocity", Category.COMBAT); }

    // TODO: анти-отдача
    @Override public void onTick() {}
}
