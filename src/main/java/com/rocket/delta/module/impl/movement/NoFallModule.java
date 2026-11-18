package com.rocket.delta.module.impl.movement;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFallModule extends Module {
    public NoFallModule() { super("NoFall", Category.MOVEMENT); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (mc.player.fallDistance > 2.0f && !mc.player.isFallFlying()) {
            mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        }
    }
}
