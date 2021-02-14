package org.faxhax.faxhax.client.modules.misc;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;

import java.util.ArrayList;

public class FaxAnnouncer extends FaxModule {

    public static FaxAnnouncer INSTANCE;

    public static FaxSetting.Boolean modules;
    FaxSetting.Mode mode;

    public FaxAnnouncer() {
        super("Announcer", FaxCategory.Misc);
    }

    @Override
    public void setup() {
        INSTANCE = this;

        ArrayList<String> modes = new ArrayList<>();
        modes.add("Client");
//        modes.add("Chat");

        mode = registerMode("Mode", modes, "Client");
        modules = registerBoolean("Toggled Modules", true);
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&&modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }
}
