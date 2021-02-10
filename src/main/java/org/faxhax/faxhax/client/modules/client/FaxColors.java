package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;

import java.awt.*;

public class FaxColors extends FaxModule {

    public FaxColors INSTANCE;


    public static FaxSetting.Integer opacity;
    public static FaxSetting.ColorSetting enabledColor;
    public static FaxSetting.ColorSetting outlineColor;
    public static FaxSetting.ColorSetting backgroundColor;
    public static FaxSetting.ColorSetting settingBackgroundColor;
    public static FaxSetting.ColorSetting fontColor;


    public FaxColors() {
        super("Colors", FaxCategory.Client);
        setDrawn(false);
        INSTANCE = this;
    }

    @Override
    public void setup() {
        opacity = registerInteger("Opacity", 150, 50, 255);
        outlineColor = registerColor("Outline", new Color(255, 0, 0, 255));
        enabledColor = registerColor("Enabled", new Color(255, 0, 0, 255));
        backgroundColor = registerColor("Background", new Color(0, 0, 0, 255));
        settingBackgroundColor = registerColor("Setting", new Color(30, 30, 30, 255));
        fontColor = registerColor("Font", new Color(255, 255, 255, 255));

    }
}