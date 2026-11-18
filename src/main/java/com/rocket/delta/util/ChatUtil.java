package com.rocket.delta.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtil {
    public static void info(String msg) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) mc.player.sendMessage(Text.literal("\u00a7c[RocketDelta] \u00a7f" + msg), false);
    }
}
