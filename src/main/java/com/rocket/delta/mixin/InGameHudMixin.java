package com.rocket.delta.mixin;

import com.rocket.delta.RocketDeltaClient;
import com.rocket.delta.event.impl.Render2DEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void rocketdelta$onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (RocketDeltaClient.INSTANCE != null)
            RocketDeltaClient.INSTANCE.eventManager.post(new Render2DEvent(context, tickCounter.getTickDelta(false)));
    }
}
