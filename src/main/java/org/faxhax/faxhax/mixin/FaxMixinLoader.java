package org.faxhax.faxhax.mixin;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.faxhax.faxhax.FaxHax;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class FaxMixinLoader implements IFMLLoadingPlugin {

    @SuppressWarnings("unused")
    private static boolean isObfuscatedEnvironment = false;

    public FaxMixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.momentum.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        FaxHax.printLog("Mixins Initialised");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() { return null; }

    @Override
    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (boolean)(Boolean)data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
