package org.faxhax.faxhax;

import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faxhax.faxhax.api.event.FaxEventManager;
import org.faxhax.faxhax.api.event.FaxEventRegister;
import org.faxhax.faxhax.api.gui.FaxGUI;
import org.faxhax.faxhax.api.module.FaxModule;
import org.faxhax.faxhax.api.module.FaxModuleManager;
import org.faxhax.faxhax.api.setting.FaxSettingManager;

@Mod(
        modid = FaxHax.MOD_ID,
        name = FaxHax.MOD_NAME,
        version = FaxHax.VERSION
)
public class FaxHax {

    @Mod.Instance
    public static FaxHax INSTANCE;
    public static Logger LOG;
    public static Minecraft MC;
    public static final String MOD_ID = "faxhax";
    public static final String MOD_NAME = "FaxHax";
    public static final String VERSION = "1.0.1";

    public static EventBus EVENTS;
    public static FaxEventManager EVENT_MANAGER;
    public static FaxSettingManager SETTINGS;
    public static FaxModuleManager MODULES;
    public static FaxGUI CLICKGUI; // GUI Last


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG = LogManager.getLogger(MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EVENTS = new EventManager();
        EVENT_MANAGER = new FaxEventManager();
        SETTINGS = new FaxSettingManager();
        MODULES = new FaxModuleManager();
        CLICKGUI = new FaxGUI(); // GUI Last
        FaxEventRegister.registerEventManager(EVENT_MANAGER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MC = Minecraft.getMinecraft();
        printLog("~~~~~~~~~~~"+MOD_NAME+"~~~~~~~~~~~");
        printLog("Running Version "+VERSION);
        printLog("Welcome to " + MOD_NAME + " " + MC.getSession().getUsername() + "!");
        printLog("Initialization complete");
        printLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printLog(String str) {
        LOG.info(str);
    }

}
