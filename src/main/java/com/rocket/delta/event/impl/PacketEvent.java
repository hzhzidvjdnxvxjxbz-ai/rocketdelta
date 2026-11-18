package com.rocket.delta.event.impl;

import com.rocket.delta.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketEvent extends Event {
    public final Packet<?> packet;
    public final boolean outgoing;
    public PacketEvent(Packet<?> packet, boolean outgoing) { this.packet = packet; this.outgoing = outgoing; }
}
