package org.faxhax.faxhax.api.module;

import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.client.modules.client.FaxClickGUI;
import org.faxhax.faxhax.client.modules.client.FaxColors;
import org.faxhax.faxhax.client.modules.render.FaxCustomFov;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FaxModuleManager {

    public static ArrayList<FaxModule> faxModules = new ArrayList<>();

    public FaxModuleManager() {
        //Combat
        addMod(new FaxClickGUI());
        addMod(new FaxColors());

        //render
        addMod(new FaxCustomFov());
    }

    public void addMod(FaxModule module) {
        faxModules.add(module);
    }

    public void onUpdate() {
        faxModules.stream().filter(FaxModule::isEnabled).forEach(FaxModule::onUpdate);
    }

    public void onRender() {
        faxModules.stream().filter(FaxModule::isEnabled).forEach(FaxModule::onRender);
        FaxHax.CLICKGUI.render();
    }

    public ArrayList<FaxModule> getModules() {
        return faxModules;
    }

    public ArrayList<FaxModule> getModulesInCategory(FaxModule.FaxCategory faxCategory) {
        ArrayList<FaxModule> list = (ArrayList<FaxModule>) getModules().stream().filter(m -> m.getCategory().equals(faxCategory)).collect(Collectors.toList());
        return list;
    }

    public void onBind(int key) {
        if (key == 0 || key == Keyboard.KEY_NONE) return;
        faxModules.forEach(module -> {
            if(module.getBind() == key) {
                module.toggle();
            }
        });
    }

    public FaxModule getModuleByName(String name) {
        FaxModule module = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return module;
    }

    public boolean isModuleEnabled(String name) {
        FaxModule module = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return module.isEnabled();
    }

    public boolean isModuleEnabled(FaxModule module) {
        return module.isEnabled();
    }

}
