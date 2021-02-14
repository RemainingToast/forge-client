package org.faxhax.faxhax.client.modules.movement;

import net.minecraft.network.play.client.CPacketPlayer;
import org.faxhax.faxhax.api.module.FaxModule;

public class FaxNoFall extends FaxModule {
    
    public FaxNoFall() {
        super("No Fall", FaxCategory.Movement);
    }

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        if (isFalling() && mc.player.fallDistance <= 3f) return;
        mc.player.connection.sendPacket(new CPacketPlayer(true));
    }

    private boolean isFalling(){
        return mc.player.fallDistance > 0.0f;
    }
}
