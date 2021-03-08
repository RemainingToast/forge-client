package org.faxhax.faxhax;

//import me.zero.alpine.fork.bus.EventBus;
//import me.zero.alpine.fork.bus.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.faxhax.faxhax.api.event.FaxEventProcessor;
import org.faxhax.faxhax.api.event.FaxForgeEvents;
import org.faxhax.faxhax.api.gui.FaxGUI;
import org.faxhax.faxhax.api.module.FaxModuleManager;
import org.faxhax.faxhax.api.setting.FaxSettingManager;
import org.faxhax.faxhax.api.util.FaxAuthUtil;
import org.faxhax.faxhax.api.util.FaxDiscord;
import org.faxhax.faxhax.api.util.text.FaxFontRenderer;
import org.faxhax.faxhax.api.util.text.FaxFontUtil;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

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

    public static FaxForgeEvents FORGE_EVENTS;
    public static FaxSettingManager SETTINGS;
    public static FaxModuleManager MODULES;
    public static FaxGUI CLICKGUI; // GUI Last

    private static FaxAuthUtil AUTH;
    private static String AUTH_KEY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOG = LogManager.getLogger(MOD_NAME);
        AUTH = new FaxAuthUtil();
        AUTH_KEY = AUTH.getEncryptedLicenseKey();

        if(!AUTH.isLicensed(AUTH_KEY)) {
            printLog("[AUTH] " + AUTH_KEY + " isn't registered!");
            printLog("[AUTH] Forcing Shutdown!");
            MC.shutdown();
        }

        Display.setTitle("FaxHax" + " v" + VERSION);
        try {
            BufferedImage originalImage=ImageIO.read(FaxHax.class.getResourceAsStream("assets/faxhax/gui/faxhax.png"));
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos );
            Display.setIcon(new ByteBuffer[] { ByteBuffer.wrap(baos.toByteArray()) });
        } catch (Exception e){
            printLog("Icon failed to load!");
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MC = Minecraft.getMinecraft();

        FORGE_EVENTS = new FaxForgeEvents();
        FORGE_EVENTS.init();
        printLog("Events Loaded");

        SETTINGS = new FaxSettingManager();
        printLog("Settings Loaded");

        MODULES = new FaxModuleManager();
        printLog("Modules Loaded");

        CLICKGUI = new FaxGUI(); // GUI Last
        printLog("Initialization Complete");

        FaxFontUtil.setFont(new FaxFontRenderer(new Font("Junction", Font.PLAIN, 18),true,true));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        FaxDiscord.INSTANCE.enable();
        printLog("~~~~~~~~~~~"+MOD_NAME+"~~~~~~~~~~~");
        printLog("Welcome to "+MOD_NAME+" "+MC.getSession().getUsername()+"!");
        printLog("Running Version "+VERSION);
        printLog("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }



    public static void printLog(String str) {
        LOG.info(str);
    }
}
