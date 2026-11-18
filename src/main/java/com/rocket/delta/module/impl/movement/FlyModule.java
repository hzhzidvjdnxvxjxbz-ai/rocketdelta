package com.rocket.delta.module.impl.movement;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import com.rocket.delta.setting.NumberSetting;

public class FlyModule extends Module {
    private final NumberSetting speed = new NumberSetting("Speed", 0.05, 0.01, 0.5, 0.01);

    public FlyModule() { super("Fly", Category.MOVEMENT); addSettings(speed); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.player.getAbilities().allowFlying = true;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(speed.getFloat());
    }

    @Override
    public void onDisable() {
        if (mc.player == null) return;
        mc.player.getAbilities().setFlySpeed(0.05f);
        if (!mc.player.isCreative() && !mc.player.isSpectator()) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
    }
}
