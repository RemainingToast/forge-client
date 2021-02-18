package org.faxhax.faxhax.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.faxhax.faxhax.FaxHax;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;


@IFMLLoadingPlugin.Name(FaxHax.MOD_NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class FaxMixinLoader implements IFMLLoadingPlugin {

    private static boolean isObfuscatedEnvironment = false;

    public FaxMixinLoader() {
        FaxHax.printLog("Mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.faxhax.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        FaxHax.printLog(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (boolean) data.get("runtimeDeobfuscationEnabled");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
