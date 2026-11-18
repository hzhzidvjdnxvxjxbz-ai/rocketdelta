package com.rocket.delta.module.impl.player;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class AutoToolModule extends Module {
    public AutoToolModule() { super("AutoTool", Category.PLAYER); }

    // TODO: авто-выбор лучшего инструмента из хотбара
    @Override public void onTick() {}
}
