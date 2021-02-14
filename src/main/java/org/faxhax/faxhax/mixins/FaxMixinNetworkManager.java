package org.faxhax.faxhax.mixins;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.event.events.FaxPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetworkManager.class)
public class FaxMixinNetworkManager {
    // Receive packet.
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void receive(ChannelHandlerContext context, net.minecraft.network.Packet packet, CallbackInfo callback) {
        FaxPacket ePacket = new FaxPacket.ReceivePacket(packet);

        System.out.println("Mixin packets are working!");
        FaxHax.EVENTS.post(ePacket);

        if (ePacket.isCancelled()) {
            callback.cancel();
        }
    }

    // Send packet.
    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void send(net.minecraft.network.Packet packet, CallbackInfo callback) {
        FaxPacket ePacket = new FaxPacket.SendPacket(packet);

        FaxHax.EVENTS.post(ePacket);

        if (ePacket.isCancelled()) {
            callback.cancel();
        }
    }

    // Exception packet.
    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void exception(ChannelHandlerContext exc, Throwable exc_, CallbackInfo callback) {
        if (exc_ instanceof Exception) {
            callback.cancel();
        }
    }
}
