package org.faxhax.faxhax.api.setting;

import org.faxhax.faxhax.api.module.FaxModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaxSettingManager {

    private final List<FaxSetting> settings;

    public FaxSettingManager() {
        this.settings = new ArrayList<>();
    }

    public List<FaxSetting> getSettings() {
        return this.settings;
    }

    public void addSetting(final FaxSetting setting) {
        this.settings.add(setting);
    }

    public FaxSetting getSettingByNameAndMod(final String name, final FaxModule parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getConfigName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public FaxSetting getSettingByNameAndModConfig(final String configname, final FaxModule parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getConfigName().equalsIgnoreCase(configname)).findFirst().orElse(null);
    }

    public List<FaxSetting> getSettingsForMod(final FaxModule parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).collect(Collectors.toList());
    }

    public List<FaxSetting> getSettingsByCategory(final FaxModule.FaxCategory faxCategory) {
        return this.settings.stream().filter(s -> s.getCategory().equals(faxCategory)).collect(Collectors.toList());
    }

    public FaxSetting getSettingByName(String name) {
        for (FaxSetting set : getSettings()) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        return null;
    }

}
