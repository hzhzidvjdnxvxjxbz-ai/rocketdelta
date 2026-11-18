package com.rocket.delta.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;

public class InventoryUtil {
    /** returns screen-handler slot id (9..44) of the item, or -1 */
    public static int findItemSlot(Item item) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return -1;
        for (int i = 0; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).isOf(item)) return i < 9 ? i + 36 : i;
        }
        return -1;
    }

    /** returns hotbar index 0..8 of the item, or -1 */
    public static int findInHotbar(Item item) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return -1;
        for (int i = 0; i < 9; i++) if (mc.player.getInventory().getStack(i).isOf(item)) return i;
        return -1;
    }

    public static void selectHotbar(int slot) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null && slot >= 0 && slot < 9) mc.player.getInventory().selectedSlot = slot;
    }
}
