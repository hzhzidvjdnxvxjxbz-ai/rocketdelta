package com.rocket.delta;

import com.rocket.delta.config.ConfigManager;
import com.rocket.delta.event.EventManager;
import com.rocket.delta.event.impl.TickEvent;
import com.rocket.delta.gui.ClickGuiScreen;
import com.rocket.delta.module.Module;
import com.rocket.delta.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RocketDeltaClient implements ClientModInitializer {
    public static final String MOD_ID = "rocketdelta";
    public static RocketDeltaClient INSTANCE;

    public EventManager eventManager;
    public ModuleManager moduleManager;
    public ConfigManager configManager;

    private final boolean[] keyState = new boolean[512];

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        eventManager = new EventManager();
        moduleManager = new ModuleManager(eventManager);
        configManager = new ConfigManager(moduleManager);
        configManager.load();

        eventManager.register(TickEvent.class, e -> handleKeys());
        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> configManager.save());
    }

    private boolean pressed(long window, int key) {
        return key > 0 && key < keyState.length && InputUtil.isKeyPressed(window, key);
    }

    private void handleKeys() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen != null || mc.getWindow() == null) return;
        long window = mc.getWindow().getHandle();

        boolean guiKey = pressed(window, GLFW.GLFW_KEY_RIGHT_SHIFT);
        if (guiKey && !keyState[GLFW.GLFW_KEY_RIGHT_SHIFT]) mc.setScreen(new ClickGuiScreen());
        keyState[GLFW.GLFW_KEY_RIGHT_SHIFT] = guiKey;

        for (Module m : moduleManager.getAll()) {
            int k = m.getKey();
            if (k <= 0 || k >= keyState.length || k == GLFW.GLFW_KEY_RIGHT_SHIFT) continue;
            boolean down = pressed(window, k);
            if (down && !keyState[k]) m.toggle();
            keyState[k] = down;
        }
    }
}
