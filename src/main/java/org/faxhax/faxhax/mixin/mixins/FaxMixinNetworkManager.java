package org.faxhax.faxhax.mixin.mixins;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraftforge.common.MinecraftForge;
import org.faxhax.faxhax.api.event.FaxEvent;
import org.faxhax.faxhax.api.event.FaxEvent.Era;
import org.faxhax.faxhax.api.event.events.FaxPacketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.ConcurrentModificationException;

@Mixin(NetworkManager.class)
public class FaxMixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        FaxPacketEvent.Send event = new FaxPacketEvent.Send(packet, Era.PRE);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
            ci.cancel();
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void onPacketReceive(ChannelHandlerContext chc, Packet<?> packet, CallbackInfo ci) {
        FaxPacketEvent.Receive event = new FaxPacketEvent.Receive(packet, Era.PRE);
        MinecraftForge.EVENT_BUS.post(event);

        if (event.isCanceled())
            ci.cancel();
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void exceptionCaught(ChannelHandlerContext exceptionCaught1, Throwable exceptionCaught2, CallbackInfo info) {
        if (exceptionCaught2 instanceof IOException || exceptionCaught2 instanceof NullPointerException || exceptionCaught2 instanceof ConcurrentModificationException)
            info.cancel();
    }
}
