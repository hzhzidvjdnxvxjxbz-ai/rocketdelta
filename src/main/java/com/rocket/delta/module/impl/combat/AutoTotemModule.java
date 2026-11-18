package com.rocket.delta.module.impl.combat;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;
import com.rocket.delta.util.InventoryUtil;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotemModule extends Module {
    public AutoTotemModule() { super("AutoTotem", Category.COMBAT); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (mc.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) return;
        int slot = InventoryUtil.findItemSlot(Items.TOTEM_OF_UNDYING);
        if (slot == -1) return;
        mc.interactionManager.clickSlot(mc.player.playerScreenHandler.syncId, slot, 40, SlotActionType.SWAP, mc.player);
    }
}
