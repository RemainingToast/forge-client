package org.faxhax.faxhax.api.gui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.*;
import com.lukflug.panelstudio.theme.ClearTheme;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.module.FaxModuleManager;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.client.modules.client.FaxClickGUI;
import org.faxhax.faxhax.client.modules.client.FaxColors;

import java.awt.*;
import java.util.ArrayList;

public class FaxGUI extends MinecraftHUDGUI {

    private final GUIInterface guiInterface;
    private final Theme theme;
    private final HUDClickGUI gui;

    public FaxGUI(){
        guiInterface=new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return "faxhax/assets";
            }

            @Override
            public void drawString(Point pos, String s, Color c) {
                end();
                FaxHax.MC.fontRenderer.drawStringWithShadow(s, pos.x, pos.y, c.getRGB());
                begin();
            }

            @Override
            public int getFontWidth(String s) {
                return FaxHax.MC.fontRenderer.getStringWidth(s);
            }

            @Override
            public int getFontHeight() {
                return FaxClickGUI.fontSize.getValue();
            }
        };
        theme=new FaxTheme(new SettingsColorScheme(
                FaxColors.enabledColor,
                FaxColors.fontColor,
                FaxColors.backgroundColor,
                FaxColors.outlineColor,
                FaxColors.fontColor,
                FaxColors.opacity
        ),height,5);
        gui=new HUDClickGUI(guiInterface, null);
        for (FaxModule.FaxCategory c: FaxModule.FaxCategory.values()){
            DraggableContainer panel=new DraggableContainer(c.name(),null, theme.getPanelRenderer(), new SimpleToggleable(false), new SettingsAnimation(FaxClickGUI.animationSpeed),null,new Point(10,10), 150);
            gui.addComponent(panel);
            for (FaxModule module: FaxHax.MODULES.getModulesInCategory(c)){
                CollapsibleContainer container=new CollapsibleContainer(module.getName(),null,theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(FaxClickGUI.animationSpeed),module);
                panel.addComponent(container);
                for (FaxSetting s: FaxHax.SETTINGS.getSettingsForMod(module)){
                    if (s instanceof Toggleable) container.addComponent(new BooleanComponent(s.getName(),null,theme.getComponentRenderer(),(Toggleable)s));
                    else if (s instanceof NumberSetting) container.addComponent(new NumberComponent(s.getName(),null,theme.getComponentRenderer(),(NumberSetting)s,((NumberSetting) s).getMinimumValue(),((NumberSetting) s).getMaximumValue()));
                    else if (s instanceof EnumSetting) container.addComponent(new EnumComponent(s.getName(),null,theme.getComponentRenderer(),(EnumSetting)s));
                    else if (s instanceof ColorSetting) container.addComponent(new ColorComponent(s.getName(),null,theme.getContainerRenderer(),new SettingsAnimation(FaxClickGUI.animationSpeed),theme.getComponentRenderer(),(ColorSetting)s,false,false,new SimpleToggleable(false)));
                }
            }
        }
    }

    @Override
    protected HUDClickGUI getHUDGUI() {
        return gui;
    }

    @Override
    protected GUIInterface getInterface() {
        return guiInterface;
    }

    @Override
    protected int getScrollSpeed() {
        return FaxClickGUI.scrollSpeed.getValue();
    }


}
