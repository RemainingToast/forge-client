package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.font.FaxFontRenderer;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class FaxClickGUI extends FaxModule {

    public static FaxSetting.Integer animationSpeed;
    public static FaxSetting.Integer scrollSpeed;
    public static FaxSetting.Boolean customFont;

    public FaxClickGUI() {
        super("ClickGUI", FaxCategory.Client);
        setDrawn(false);
        setBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void setup() {
        animationSpeed = registerInteger("Animation Speed", 200, 0, 1000);
        scrollSpeed = registerInteger("Scroll Speed", 10, 1, 20);
        customFont = registerBoolean("Custom Font", true);
    }

    @Override
    protected void onEnable() {
        FaxHax.CLICKGUI.enterGUI();
        disable();
    }
}
