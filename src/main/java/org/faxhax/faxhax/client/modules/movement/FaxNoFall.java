package org.faxhax.faxhax.client.modules.movement;

import net.minecraft.network.play.client.CPacketPlayer;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.util.ArrayList;
import java.util.List;

public class FaxNoFall extends FaxModule {

    FaxSetting.Mode mode;

    public FaxNoFall() {
        super("No Fall", FaxCategory.Movement);
        setHudInfo(mode.getValue());
    }

    @Override
    public void setup() {
        List<String> modes = new ArrayList<>();
        modes.add("Packet");

        mode = registerMode("Mode", modes, "Packet");
    }

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        if(mode.getValue().equalsIgnoreCase("Packet")){
            if (isFalling() && mc.player.fallDistance <= 3f) return;
            mc.player.connection.sendPacket(new CPacketPlayer(true));
        }
    }

    private boolean isFalling(){
        return mc.player.fallDistance > 0.0f;
    }
}
