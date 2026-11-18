package com.rocket.delta.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationUtil {
    /** returns {yaw, pitch} from one point to another */
    public static float[] getRotations(Vec3d from, Vec3d to) {
        double dx = to.x - from.x, dy = to.y - from.y, dz = to.z - from.z;
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, dist));
        return new float[]{MathHelper.wrapDegrees(yaw), MathHelper.clamp(pitch, -90f, 90f)};
    }
}
