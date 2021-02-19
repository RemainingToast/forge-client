package org.faxhax.faxhax.client.modules.render;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;
import org.faxhax.faxhax.client.modules.misc.FaxAnnouncer;

public class FaxShulkerPreview extends FaxModule {
    public FaxShulkerPreview() {
        super("ShulkerPreview", FaxCategory.Render);
    }

    @Override
    public void onToggle() {
        if(mc.player!=null&& FaxAnnouncer.modules.getValue()&&FaxAnnouncer.INSTANCE.isOn()) FaxMessageUtil.toggleMessage(this);
    }
}
