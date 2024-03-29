package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.render.FaxColor;

public class FaxColors extends FaxModule {

    public FaxColors INSTANCE;


    public static FaxSetting.Integer opacity;
    public static FaxSetting.ColorSetting enabledColor;
    public static FaxSetting.ColorSetting categoryBgColor;
    public static FaxSetting.ColorSetting outlineColor;
    public static FaxSetting.ColorSetting backgroundColor;
    public static FaxSetting.ColorSetting settingBackgroundColor;
    public static FaxSetting.ColorSetting fontColor;


    public FaxColors() {
        super("Colors", FaxCategory.Client);
        setEnabled(true);
        setDrawn(false);
        INSTANCE = this;
    }

    @Override
    public void setup() {
        opacity = registerInteger("Opacity", 201, 50, 255);
        enabledColor = registerColor("Enabled", new FaxColor(200, 30, 30, 255));
        categoryBgColor = registerColor("Category BG", new FaxColor(0, 90, 170, 255));
        outlineColor = registerColor("Outline", new FaxColor(0,0,0,255));
        backgroundColor = registerColor("Background", new FaxColor(0, 0, 0, 255));
        settingBackgroundColor = registerColor("Setting", new FaxColor(30, 30, 30, 255));
        fontColor = registerColor("Font", new FaxColor(255, 255, 255, 255));
    }

    @Override
    protected void onDisable() {
        enable();
    }
}