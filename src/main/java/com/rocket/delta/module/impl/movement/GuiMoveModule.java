package com.rocket.delta.module.impl.movement;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class GuiMoveModule extends Module {
    public GuiMoveModule() { super("GuiMove", Category.MOVEMENT); }

    // TODO: ходьба с открытым инвентарем
    @Override public void onTick() {}
}
