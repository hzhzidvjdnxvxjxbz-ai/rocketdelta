package com.rocket.delta.module.impl.player;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class FastBreakModule extends Module {
    public FastBreakModule() { super("FastBreak", Category.PLAYER); }

    // TODO: ускорение ломания блоков (InteractionManagerMixin)
    @Override public void onTick() {}
}
