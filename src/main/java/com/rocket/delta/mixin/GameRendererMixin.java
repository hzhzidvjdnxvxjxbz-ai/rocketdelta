package com.rocket.delta.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;

// TODO: NoRender (тряска камеры) и точка вызова Render3DEvent
@Mixin(GameRenderer.class)
public class GameRendererMixin {
}
