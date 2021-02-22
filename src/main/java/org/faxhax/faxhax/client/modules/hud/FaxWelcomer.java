package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.math.FaxMathUtil;
import org.faxhax.faxhax.api.util.render.FaxColor;
import org.faxhax.faxhax.api.util.text.FaxFontUtil;

import java.awt.*;

public class FaxWelcomer extends FaxHUDModule {

    private static FaxSetting.ColorSetting color;
    private static final String string = FaxMathUtil.getTimeOfDay() + mc.session.getUsername() + " welcome to the vibe zone";


    public FaxWelcomer() {
        super("Welcomer", new Point(getCenteredX(), 5));
    }

    private static int getCenteredX(){
        Rectangle rect = new Rectangle(mc.displayWidth, mc.displayHeight);
        int width = FaxFontUtil.getStringWidth(FaxWelcomer.string);
        return (int) rect.getCenterX() - (width - width / 4);
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
