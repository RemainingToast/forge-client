package org.faxhax.faxhax.client.modules.misc;

import net.minecraft.util.EnumHand;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;

import java.util.ArrayList;

public class FaxOffHandSwing extends FaxModule {
    public FaxOffHandSwing() {
        super("OffHand Swing", FaxCategory.Misc);
        setDrawn(true);
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&&FaxAnnouncer.modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }

    public void onUpdate() {
        if(mc.world == null)
            return;

        mc.player.swingingHand = EnumHand.OFF_HAND;
    }
}
