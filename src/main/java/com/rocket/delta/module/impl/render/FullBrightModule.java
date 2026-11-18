package com.rocket.delta.module.impl.render;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FullBrightModule extends Module {
    public FullBrightModule() { super("FullBright", Category.RENDER); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 500, 0, false, false, false));
    }

    @Override
    public void onDisable() {
        if (mc.player != null) mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
