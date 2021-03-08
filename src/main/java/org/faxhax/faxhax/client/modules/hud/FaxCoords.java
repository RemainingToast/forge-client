package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.entity.FaxPlayerUtil;

import java.awt.*;

public class FaxCoords extends FaxHUDModule {

    public FaxCoords() {
        super("Coords", new Point(50, 50));
    }

    @Override
    public void setup() {

    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FaxCoordinates());
    }

    private static class FaxCoordinates implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return FaxPlayerUtil.getPlayerPosFormatted();
        }

        @Override
        public Color getItemColor(int index) {
            return new Color(255,255,255);
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
