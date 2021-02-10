package org.faxhax.faxhax.client.modules.client;

import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.lwjgl.input.Keyboard;

public class FaxClickGUI extends FaxModule {

    public static FaxSetting.Integer animationSpeed;
    public static FaxSetting.Integer scrollSpeed;
    public static FaxSetting.Integer fontSize;

    public FaxClickGUI() {
        super("ClickGUI", FaxCategory.Client);
        setDrawn(false);
        setBind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void setup() {
        animationSpeed = registerInteger("Animation Speed", 200, 0, 1000);
        scrollSpeed = registerInteger("Scroll Speed", 10, 1, 20);
        fontSize = registerInteger("Font Size", 10,2,20);
    }

    @Override
    protected void onEnable() {
        FaxHax.CLICKGUI.enterGUI();
        disable();
    }
}
