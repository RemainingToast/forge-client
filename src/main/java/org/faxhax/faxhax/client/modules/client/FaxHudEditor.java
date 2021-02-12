package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;

public class FaxHudEditor extends FaxModule {
    public FaxHudEditor() {
        super("HUD Editor", FaxCategory.Client);
        setDrawn(false);
    }

    @Override
    protected void onEnable() {
        FaxHax.CLICKGUI.enterHUDEditor();
        disable();
    }
}
