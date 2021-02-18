package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.render.FaxColor;

import java.awt.*;

public class FaxWatermark extends FaxHUDModule {

    private static FaxSetting.ColorSetting color;

    public FaxWatermark() {
        super("Watermark", new Point(1,3));
    }

    @Override
    public void setup() {
        color = registerColor("Color", new FaxColor(0,170,170));
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FaxWatermarkList());
    }


    private static class FaxWatermarkList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return FaxHax.MOD_NAME + " v" + FaxHax.VERSION;
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
