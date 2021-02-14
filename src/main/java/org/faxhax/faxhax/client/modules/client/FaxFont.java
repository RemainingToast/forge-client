package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.util.ArrayList;
import java.util.List;

public class FaxFont extends FaxModule {

    public static FaxFont INSTANCE;

    FaxSetting.Mode fontType;

    public FaxFont() {
        super("Font", FaxCategory.Client);
        setDrawn(false);
    }

    @Override
    public void setup() {
        INSTANCE = this;

        List<String> fonts = new ArrayList<>();
        fonts.add("Junction");

        fontType = registerMode("Type", fonts,"Junction");
    }
}
