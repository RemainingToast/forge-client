package org.faxhax.faxhax.api.module;

import com.lukflug.panelstudio.settings.KeybindSetting;
import com.lukflug.panelstudio.settings.Toggleable;
import net.minecraft.client.Minecraft;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.setting.FaxSetting;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.List;

public abstract class FaxModule implements Toggleable, KeybindSetting {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    String name;
    FaxCategory faxCategory;
    int bind;
    boolean enabled;
    boolean drawn;

    public FaxModule(String name, FaxCategory faxCategory) {
        this.name = name;
        this.faxCategory = faxCategory;
        this.bind = Keyboard.KEY_NONE;
        this.enabled = false;
        this.drawn = true;
        setup();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public FaxCategory getCategory() {
        return this.faxCategory;
    }

    public void setCategory(FaxCategory faxCategory) {
        this.faxCategory = faxCategory;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int bind){
        this.bind = bind;
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }

    public void onUpdate() {

    }

    public void onRender() {

    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void enable() {
        setEnabled(true);
        onEnable();
    }

    public void disable() {
        setEnabled(false);
        onDisable();
    }

    public void toggle() {
        if(isEnabled()) {
            disable();
        }
        else if(!isEnabled()) {
            enable();
        }
    }

    public String getHudInfo() {
        return "";
    }

    public void setup() {

    }

    public boolean isDrawn() {
        return this.drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    /** Check FaxSetting.java */

    protected FaxSetting.Integer registerInteger(final String name, final int value, final int min, final int max) {
        final FaxSetting.Integer setting = new FaxSetting.Integer(name, this, getCategory(), value, min, max);
        FaxHax.SETTINGS.addSetting(setting);
        return setting;
    }

    protected FaxSetting.Double registerDouble(final String name, final double value, final double min, final double max) {
        final FaxSetting.Double setting = new FaxSetting.Double(name, this, getCategory(), value, min, max);
        FaxHax.SETTINGS.addSetting(setting);
        return setting;
    }

    protected FaxSetting.Boolean registerBoolean(final String name, final boolean value) {
        final FaxSetting.Boolean setting = new FaxSetting.Boolean(name, this, getCategory(), value);
        FaxHax.SETTINGS.addSetting(setting);
        return setting;
    }

    protected FaxSetting.Mode registerMode(final String name, final List<String> modes, final String value) {
        final FaxSetting.Mode setting = new FaxSetting.Mode(name, this, getCategory(), modes, value);
        FaxHax.SETTINGS.addSetting(setting);
        return setting;
    }

    protected FaxSetting.ColorSetting registerColor (final String name, Color color) {
        final FaxSetting.ColorSetting setting = new FaxSetting.ColorSetting(name, this, getCategory(), false, color);
        FaxHax.SETTINGS.addSetting(setting);
        return setting;
    }

    protected FaxSetting.ColorSetting registerColor (final String name) {
        return registerColor(name, new Color(90,145,240));
    }

    public enum FaxCategory {
        COMBAT,
        Exploits,
        Movement,
        Misc,
        Render,
        HUD,
        Client
    }

    @Override
    public boolean isOn() {
        return this.enabled;
    }

    @Override
    public int getKey() {
        return this.bind;
    }

    @Override
    public void setKey(int key) {
        this.bind = key;
    }

    @Override
    public String getKeyName() {
        return Keyboard.getKeyName(this.bind);
    }
}
