package com.rocket.delta.module.impl.render;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class NoRenderModule extends Module {
    public NoRenderModule() { super("NoRender", Category.RENDER); }

    // TODO: отключение огня, тумана, тряски камеры
    @Override public void onTick() {}
}
