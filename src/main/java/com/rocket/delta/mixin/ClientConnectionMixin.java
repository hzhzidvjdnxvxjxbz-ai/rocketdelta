package com.rocket.delta.mixin;

import com.rocket.delta.RocketDeltaClient;
import com.rocket.delta.event.impl.PacketEvent;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void rocketdelta$onSend(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo ci) {
        if (RocketDeltaClient.INSTANCE == null) return;
        PacketEvent e = RocketDeltaClient.INSTANCE.eventManager.post(new PacketEvent(packet, true));
        if (e.isCancelled()) ci.cancel();
    }

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void rocketdelta$onReceive(ChannelHandlerContext ctx, Packet<?> packet, CallbackInfo ci) {
        if (RocketDeltaClient.INSTANCE == null) return;
        PacketEvent e = RocketDeltaClient.INSTANCE.eventManager.post(new PacketEvent(packet, false));
        if (e.isCancelled()) ci.cancel();
    }
}
