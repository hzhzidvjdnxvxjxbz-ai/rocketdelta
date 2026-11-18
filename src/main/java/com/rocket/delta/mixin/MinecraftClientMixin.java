package com.rocket.delta.mixin;

import com.rocket.delta.RocketDeltaClient;
import com.rocket.delta.event.impl.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void rocketdelta$onTick(CallbackInfo ci) {
        if (RocketDeltaClient.INSTANCE != null) RocketDeltaClient.INSTANCE.eventManager.post(new TickEvent());
    }
}
