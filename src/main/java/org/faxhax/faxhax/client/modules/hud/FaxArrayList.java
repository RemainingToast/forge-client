package org.faxhax.faxhax.client.modules.hud;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.render.FaxColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FaxArrayList extends FaxHUDModule {

    private FaxSetting.Boolean sortUp;
    private FaxSetting.Boolean sortRight;
    private FaxSetting.ColorSetting color;
    private FaxModuleList list = new FaxModuleList();

    public FaxArrayList() {
        super("ArrayList", new Point(100,100));
        sortUp = registerBoolean("Sort Up", true);
        sortRight = registerBoolean("Sort Right", false);
        color = registerColor("Color", new FaxColor(255, 0, 0, 255));
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(),position,list);
    }

    public void onRender() {
        list.activeModules.clear();
        for (FaxModule module: FaxHax.MODULES.getModules()) {
            if (module.isOn() && module.isDrawn() && module.getCategory() != FaxCategory.HUD) list.activeModules.add(module);
        }
        list.activeModules.sort(Comparator.comparing(module -> -FaxHax.CLICKGUI.guiInterface.getFontWidth(module.getName()+module.getHudInfo())));
    }

    private class FaxModuleList implements HUDList {

        public List<FaxModule> activeModules = new ArrayList<>();

        @Override
        public int getSize() {
            return activeModules.size();
        }

        @Override
        public String getItem(int index) {
            FaxModule module = activeModules.get(index);
            return module.getName() + module.getHudInfo();
        }

        @Override
        public FaxColor getItemColor(int index) {
            FaxColor c = color.getValue();
            return FaxColor.fromHSB(c.getHue() + (color.getRainbow() ? .02f * index : 0), c.getSaturation(), c.getBrightness());
        }

        @Override
        public boolean sortUp() {
            return sortUp.isOn();
        }

        @Override
        public boolean sortRight() {
            return sortRight.isOn();
        }
    }
}
