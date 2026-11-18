package com.rocket.delta.module.impl.render;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class EspModule extends Module {
    public EspModule() { super("ESP", Category.RENDER); }

    // TODO: подсветка игроков, мобов и сундуков (Render3DEvent)
    @Override public void onTick() {}
}
