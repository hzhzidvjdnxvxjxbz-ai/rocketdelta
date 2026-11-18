package com.rocket.delta.module.impl.combat;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import com.rocket.delta.setting.BooleanSetting;
import com.rocket.delta.setting.ModeSetting;
import com.rocket.delta.setting.NumberSetting;
import com.rocket.delta.util.RotationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KillAuraModule extends Module {
    private final NumberSetting range = new NumberSetting("Range", 4.0, 1.0, 6.0, 0.1);
    private final ModeSetting mode = new ModeSetting("Mode", "Single", "Single", "Multi");
    private final ModeSetting priority = new ModeSetting("Priority", "Distance", "Distance", "Health");
    private final BooleanSetting players = new BooleanSetting("Players", true);
    private final BooleanSetting mobs = new BooleanSetting("Mobs", false);
    private final BooleanSetting walls = new BooleanSetting("ThroughWalls", false);
    private final BooleanSetting rotate = new BooleanSetting("Rotate", true);
    private final NumberSetting rotSpeed = new NumberSetting("RotSpeed", 90, 10, 180, 5);
    private final BooleanSetting cooldown = new BooleanSetting("WaitCooldown", true);
    private final NumberSetting multiMax = new NumberSetting("MultiTargets", 3, 1, 6, 1);

    public KillAuraModule() {
        super("KillAura", Category.COMBAT);
        addSettings(range, mode, priority, players, mobs, walls, rotate, rotSpeed, cooldown, multiMax);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.player.isDead()) return;

        List<LivingEntity> targets = new ArrayList<>();
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity le) || e == mc.player || !le.isAlive()) continue;
            if (e instanceof ArmorStandEntity) continue;
            boolean isPlayer = e instanceof PlayerEntity;
            if (isPlayer && (!players.get() || ((PlayerEntity) e).isSpectator())) continue;
            if (!isPlayer && !(mobs.get() && e instanceof MobEntity)) continue;
            if (mc.player.distanceTo(e) > range.get()) continue;
            if (!walls.get() && !visible(le)) continue;
            targets.add(le);
        }
        if (targets.isEmpty()) return;

        Comparator<LivingEntity> cmp = priority.is("Health")
            ? Comparator.comparingDouble(LivingEntity::getHealth)
            : Comparator.comparingDouble(e -> mc.player.distanceTo(e));
        targets.sort(cmp);

        LivingEntity main = targets.get(0);
        if (rotate.get() && !rotateTo(main)) return;

        if (cooldown.get() && mc.player.getAttackCooldownProgress(0.5f) < 0.92f) return;

        int count = mode.is("Multi") ? Math.min(targets.size(), multiMax.getInt()) : 1;
        for (int i = 0; i < count; i++) mc.interactionManager.attackEntity(mc.player, targets.get(i));
        mc.player.swingHand(Hand.MAIN_HAND);
    }

    /** smooth turn toward target; returns true when aimed closely enough to attack */
    private boolean rotateTo(LivingEntity t) {
        Vec3d aim = t.getPos().add(0, t.getHeight() * 0.6, 0);
        float[] r = RotationUtil.getRotations(mc.player.getEyePos(), aim);
        float sp = rotSpeed.getFloat();
        float dy = MathHelper.wrapDegrees(r[0] - mc.player.getYaw());
        float dp = r[1] - mc.player.getPitch();
        mc.player.setYaw(mc.player.getYaw() + MathHelper.clamp(dy, -sp, sp));
        mc.player.setPitch(MathHelper.clamp(mc.player.getPitch() + MathHelper.clamp(dp, -sp, sp), -90f, 90f));
        float ey = MathHelper.wrapDegrees(r[0] - mc.player.getYaw());
        float ep = r[1] - mc.player.getPitch();
        return Math.abs(ey) < 15f && Math.abs(ep) < 20f;
    }

    private boolean visible(LivingEntity e) {
        Vec3d from = mc.player.getEyePos();
        Vec3d to = e.getPos().add(0, e.getHeight() * 0.5, 0);
        return mc.world.raycast(new RaycastContext(from, to, RaycastContext.ShapeType.COLLIDER,
            RaycastContext.FluidHandling.NONE, mc.player)).getType() == HitResult.Type.MISS;
    }
}
