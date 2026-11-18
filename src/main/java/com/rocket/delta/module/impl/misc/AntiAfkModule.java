package com.rocket.delta.module.impl.misc;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import net.minecraft.util.Hand;

public class AntiAfkModule extends Module {
    private int ticks;
    public AntiAfkModule() { super("AntiAFK", Category.MISC); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (++ticks >= 600) {
            ticks = 0;
            mc.player.swingHand(Hand.MAIN_HAND);
            if (mc.player.isOnGround()) mc.player.jump();
        }
    }
}
