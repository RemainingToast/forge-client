package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.render.FaxColor;

import java.awt.*;

import static org.faxhax.faxhax.api.util.entity.FaxPlayerUtil.getTotemCount;

public class FaxTotems extends FaxHUDModule {

    private static FaxSetting.ColorSetting color;

    public FaxTotems() {
        super("Totems", new Point(2,mc.displayHeight / 2));
    }

    @Override
    public void setup() {
        color = registerColor("Color", new FaxColor(255,255,255));
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FaxTotemsList());
    }

    private static class FaxTotemsList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return "Totems: "+getTotemCount();
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
