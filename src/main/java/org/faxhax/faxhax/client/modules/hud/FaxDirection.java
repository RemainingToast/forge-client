package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import net.minecraft.util.text.TextFormatting;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.util.entity.FaxPlayerUtil;

import java.awt.*;

public class FaxDirection extends FaxHUDModule {

    public FaxDirection() {
        super("Direction", new Point(20,20));
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new FaxDirectionList());
    }

    private static class FaxDirectionList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return FaxPlayerUtil.getDirection().toString()
                    + TextFormatting.GRAY + " [" + TextFormatting.WHITE + FaxPlayerUtil.getAxis() + TextFormatting.GRAY + "]";
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
