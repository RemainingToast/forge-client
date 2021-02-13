package org.faxhax.faxhax.api.gui;

import com.lukflug.panelstudio.*;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import com.lukflug.panelstudio.hud.HUDPanel;
import com.lukflug.panelstudio.mc12.GLInterface;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.settings.*;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.module.FaxHUDModule;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.faxhax.faxhax.api.util.FaxColor;
import org.faxhax.faxhax.api.util.font.FaxFontUtil;
import org.faxhax.faxhax.client.modules.client.FaxClickGUI;
import org.faxhax.faxhax.client.modules.client.FaxColors;

import java.awt.*;
import java.util.ArrayList;

public class FaxGUI extends MinecraftHUDGUI {

    public static final int WIDTH=100,HEIGHT=10,DISTANCE=10,HUD_BORDER=2;
    public final GUIInterface guiInterface;
    private final Theme theme;
    private final HUDClickGUI gui;
    private final Toggleable hud;

    public FaxGUI(){
        theme=new FaxTheme(new SettingsColorScheme(FaxColors.enabledColor, FaxColors.backgroundColor,FaxColors.settingBackgroundColor,FaxColors.outlineColor,FaxColors.fontColor,FaxColors.opacity),HEIGHT,2);
        guiInterface=new GUIInterface(true) {
            @Override
            public void drawString(Point pos, String s, Color c) {
                GLInterface.end();
                int x=pos.x+2, y=pos.y+2;
//                if (!FaxClickGUI.customFont.getValue()) {
//                    x+=1;
//                }
                FaxFontUtil.drawStringWithShadow(FaxClickGUI.customFont.getValue(),s,x,y,new FaxColor(c));
                GLInterface.begin();
            }

            @Override
            public int getFontWidth(String s) {
                return Math.round(FaxFontUtil.getStringWidth(FaxClickGUI.customFont.getValue(),s))+4;
            }

            @Override
            public int getFontHeight() {
                return Math.round(FaxFontUtil.getFontHeight(FaxClickGUI.customFont.getValue()))+2;
            }

            @Override
            public String getResourcePrefix() {
                return "faxhax:gui/";
            }
        };
        gui=new HUDClickGUI(guiInterface, null){
            @Override
            public void handleScroll(int diff) {
                for (FixedComponent component: components) {
                    if (!hudComponents.contains(component)) {
                        Point p = component.getPosition(guiInterface);
                        p.translate(0, -diff);
                        component.setPosition(guiInterface, p);
                    }
                }
            }
        };
        hud=new Toggleable() {
            @Override
            public void toggle() {
            }

            @Override
            public boolean isOn() {
                return hudEditor;
            }
        };
        for (FaxModule module: FaxHax.MODULES.getModules()) {
            if (module instanceof FaxHUDModule) {
                ((FaxHUDModule)module).populate(theme);
                gui.addHUDComponent(new HUDPanel(((FaxHUDModule)module).getComponent(),theme.getPanelRenderer(),module,new SettingsAnimation(FaxClickGUI.animationSpeed),hud,HUD_BORDER));
            }
        }
        Point pos=new Point(DISTANCE,DISTANCE);
        for (FaxModule.FaxCategory category: FaxModule.FaxCategory.values()) {
            ArrayList<FaxModule> mods = FaxHax.MODULES.getModulesInCategory(category);
            if(mods.size() == 0) continue;
            DraggableContainer panel=new DraggableContainer(category.name(),null,theme.getPanelRenderer(),new SimpleToggleable(false),new SettingsAnimation(FaxClickGUI.animationSpeed),null,new Point(pos),WIDTH) {
                @Override
                protected int getScrollHeight (int childHeight) {
                    return Math.min(childHeight,Math.max(HEIGHT*4,FaxGUI.this.height-getPosition(guiInterface).y-renderer.getHeight(open.getValue()!=0)-HEIGHT));
                }

                @Override
                public void render(Context context) {
                    if(category!= FaxModule.FaxCategory.HUD || hudEditor) super.render(context);
                }

                @Override
                public void handleKey(Context context, int scancode) {
                    if(category!= FaxModule.FaxCategory.HUD || hudEditor) super.handleKey(context,scancode);
                }
            };
            if(category!= FaxModule.FaxCategory.HUD) gui.addComponent(panel);
            else gui.addHUDComponent(panel);
            pos.translate(WIDTH+DISTANCE,0);
            for (FaxModule module: mods) {
                addModule(panel,module);
            }
        }
    }

    private void addModule(CollapsibleContainer panel, FaxModule module) {
        CollapsibleContainer container=new CollapsibleContainer(module.getName(),null,theme.getContainerRenderer(),new SimpleToggleable(false),new SettingsAnimation(FaxClickGUI.animationSpeed),module);
        panel.addComponent(container);
        for (FaxSetting property: FaxHax.SETTINGS.getSettingsForMod(module)) {
            if (property instanceof FaxSetting.Boolean) {
                container.addComponent(new BooleanComponent(property.getName(),null,theme.getComponentRenderer(),(FaxSetting.Boolean)property));
            } else if (property instanceof FaxSetting.Integer) {
                container.addComponent(new NumberComponent(property.getName(),null,theme.getComponentRenderer(),(FaxSetting.Integer)property,((FaxSetting.Integer)property).getMin(),((FaxSetting.Integer)property).getMax()));
            } else if (property instanceof FaxSetting.Double) {
                container.addComponent(new NumberComponent(property.getName(),null,theme.getComponentRenderer(),(FaxSetting.Double)property,((FaxSetting.Double)property).getMin(),((FaxSetting.Double)property).getMax()));
            } else if (property instanceof FaxSetting.Mode) {
                container.addComponent(new EnumComponent(property.getName(),null,theme.getComponentRenderer(),(FaxSetting.Mode)property));
            } else if (property instanceof FaxSetting.ColorSetting) {
                container.addComponent(new ColorComponent(property.getName(),null,theme.getComponentRenderer(), new SettingsAnimation(FaxClickGUI.animationSpeed), theme.getComponentRenderer(), (FaxSetting.ColorSetting)property, true, true, new SimpleToggleable(false)));
            }
        }
      container.addComponent(new FaxKeyBind(theme.getComponentRenderer(),module));
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
        return 5;
    }


}
