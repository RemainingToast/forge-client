package org.faxhax.faxhax.client.modules.misc;

import net.minecraft.util.EnumHand;
import org.faxhax.faxhax.api.module.FaxModule;

import java.util.ArrayList;

public class FaxOffHandSwing extends FaxModule {
    public FaxOffHandSwing() {
        super("OffHandSwing", FaxCategory.Misc);
        setDrawn(true);
    }

    public void onUpdate() {
        if(mc.world == null)
            return;

        mc.player.swingingHand = EnumHand.OFF_HAND;
    }
}
