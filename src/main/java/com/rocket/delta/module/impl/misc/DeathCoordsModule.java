package com.rocket.delta.module.impl.misc;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import com.rocket.delta.util.ChatUtil;

public class DeathCoordsModule extends Module {
    private boolean reported;
    public DeathCoordsModule() { super("DeathCoords", Category.MISC); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.getHealth() <= 0 && !reported) {
            reported = true;
            ChatUtil.info(String.format("Death: %d %d %d", (int) mc.player.getX(), (int) mc.player.getY(), (int) mc.player.getZ()));
        } else if (mc.player.getHealth() > 0) {
            reported = false;
        }
    }
}
