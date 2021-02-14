package org.faxhax.faxhax.client.modules.combat;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.text.TextFormatting;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.event.FaxEventCancellable;
import org.faxhax.faxhax.api.event.events.FaxPacket;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import scala.Int;

public class FaxVelocity extends FaxModule {

    FaxSetting.Double horizontal;
    FaxSetting.Double vertical;
    FaxSetting.Integer delay;

    public FaxVelocity() {
        super("Velocity", FaxCategory.Movement);
        setHudInfo("H"+horizontal.getValue()+"%"+TextFormatting.DARK_GRAY+"|"+TextFormatting.WHITE+"V"+vertical.getValue()+"%");
    }

    @Override
    public void setup() {
        horizontal = registerDouble("Horizontal %", 0.0,0.0,100.0);
        vertical = registerDouble("Vertical %", 0.0,0.0,100.0);
        delay = registerInteger("Delay ms", 170,0,1000);
    }

    @Override
    protected void onEnable() {
        FaxHax.EVENTS.subscribe(receivePacket);
    }

    @Override
    protected void onDisable() {
        FaxHax.EVENTS.unsubscribe(receivePacket);
    }

    @EventHandler
    private final Listener<FaxPacket.ReceivePacket> receivePacket = new Listener<>(event -> {
        if(mc.player==null) return;
        double oldVelX = Double.NaN;
        double oldVelY = Double.NaN;
        double oldVelZ = Double.NaN;
        if(event.getEra() == FaxEventCancellable.Era.EVENT_PRE){
            if(event.getPacket() instanceof SPacketEntityVelocity){
                SPacketEntityVelocity packet = (SPacketEntityVelocity) event.getPacket();
                if(packet.getEntityID() != mc.player.getEntityId()) return;
                if(horizontal.getValue() == 0.0 && vertical.getValue() == 0.0) {
                    event.cancel();
                    return;
                }
                if(delay.getValue() > 0){
                    oldVelX = (packet.motionX * horizontal.getValue()) / 8000.0;
                    oldVelY = (packet.motionY * vertical.getValue()) / 8000.0;
                    oldVelZ = (packet.motionZ * horizontal.getValue()) / 8000.0;
                } else {
                    packet.motionX = Integer.parseInt(String.valueOf(packet.motionX * horizontal.getValue()));
                    packet.motionY = Integer.parseInt(String.valueOf(packet.motionY * vertical.getValue()));
                    packet.motionZ = Integer.parseInt(String.valueOf(packet.motionZ * horizontal.getValue()));
                }
            }
        } else if(event.getPacket() instanceof SPacketExplosion){
            SPacketExplosion packet = (SPacketExplosion) event.getPacket();
            if(horizontal.getValue() == 0.0 && vertical.getValue() == 0.0) {
                event.cancel();
                return;
            }
            if(delay.getValue() > 0){
                oldVelX = Float.parseFloat(String.valueOf(packet.motionX));
                oldVelY = Float.parseFloat(String.valueOf(packet.motionY));
                oldVelZ = Float.parseFloat(String.valueOf(packet.motionZ));
                event.cancel();
                return;
            } else {
                packet.motionX = Float.parseFloat(String.valueOf(packet.motionX * horizontal.getValue()));
                packet.motionY = Float.parseFloat(String.valueOf(packet.motionY * vertical.getValue()));
                packet.motionZ = Float.parseFloat(String.valueOf(packet.motionZ * horizontal.getValue()));
            }
        }
        if (delay.getValue() > 0 && oldVelX != 0.0 || oldVelY == 0.0 || oldVelZ == 0.0) {
            try {
                Thread.sleep(Long.parseLong(String.valueOf(delay.getValue())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mc.player.setVelocity(mc.player.motionX + oldVelX, mc.player.motionY + oldVelY, mc.player.motionZ + oldVelZ);
        }
    });

}
