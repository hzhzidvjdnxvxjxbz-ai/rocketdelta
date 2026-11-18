package com.rocket.delta.module.impl.render;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class XrayModule extends Module {
    public XrayModule() { super("Xray", Category.RENDER); }

    // TODO: видимость руд сквозь блоки (WorldRendererMixin)
    @Override public void onTick() {}
}
