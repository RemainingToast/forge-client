package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;
import org.faxhax.faxhax.client.modules.misc.FaxAnnouncer;

public class FaxHudEditor extends FaxModule {
    public FaxHudEditor() {
        super("HUDEditor", FaxCategory.Client);
        setDrawn(false);
    }

    @Override
    protected void onEnable() {
        FaxHax.CLICKGUI.enterHUDEditor();
        disable();
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&&FaxAnnouncer.modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }
}
