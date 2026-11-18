package com.rocket.delta.module.impl.movement;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import com.rocket.delta.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class SpeedModule extends Module {
    private final NumberSetting speed = new NumberSetting("Speed", 0.3, 0.1, 1.0, 0.05);

    public SpeedModule() { super("Speed", Category.MOVEMENT); addSettings(speed); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        float f = mc.player.input.movementForward;
        float s = mc.player.input.movementSideways;
        if (f == 0 && s == 0) return;
        double yaw = Math.toRadians(mc.player.getYaw());
        double x = -Math.sin(yaw) * f + Math.cos(yaw) * s;
        double z = Math.cos(yaw) * f + Math.sin(yaw) * s;
        double len = Math.sqrt(x * x + z * z);
        Vec3d v = mc.player.getVelocity();
        mc.player.setVelocity(x / len * speed.get(), v.y, z / len * speed.get());
    }
}
