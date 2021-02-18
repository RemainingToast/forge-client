package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.render.FaxColor;
import org.faxhax.faxhax.api.util.text.FaxFontUtil;
import org.faxhax.faxhax.client.modules.client.FaxFont;

import java.awt.*;

public class FaxWelcomer extends FaxHUDModule {

    private static FaxSetting.ColorSetting color;
    private static final String string = "Welcome to the vibe zone "+ mc.session.getUsername();


    public FaxWelcomer() {
        super("Welcomer", new Point((mc.displayWidth / 2) - FaxFontUtil.getStringWidth(FaxFont.INSTANCE.isOn(), string), 5));
    }

    @Override
    public void setup() {
        color = registerColor("Color", new FaxColor(0,255,0));
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FaxWelcomerList());
    }

    private static class FaxWelcomerList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return string;
        }

        @Override
        public Color getItemColor(int index) {
            return color.getValue();
        }

        @Override
        public boolean sortUp() {
            return false;
        }

        @Override
        public boolean sortRight() {
            return false;
        }
    }

}
