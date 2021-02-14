package org.faxhax.faxhax.client.modules.misc;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;

public class FaxAnnouncer extends FaxModule {

    public static FaxAnnouncer INSTANCE;

    public static FaxSetting.Boolean modules;

    public FaxAnnouncer() {
        super("Announcer", FaxCategory.Misc);
    }

    @Override
    public void setup() {
        INSTANCE = this;
        modules = registerBoolean("Toggled Modules", true);
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&&modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }
}
