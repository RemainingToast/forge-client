package org.faxhax.faxhax.api.util;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;
import net.minecraft.client.Minecraft;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.util.entity.FaxPlayerUtil;
import org.faxhax.faxhax.api.util.text.FaxMessageUtil;

import java.util.UUID;

public class FaxDiscord {
    public static final FaxDiscord INSTANCE = new FaxDiscord();

    private final DiscordRPC RPC = DiscordRPC.INSTANCE;
    private final String APP_ID = "810145025148715028";
    private final String SECRET = UUID.randomUUID().toString();
    private final long START_TIME = System.currentTimeMillis();
    private Thread THREAD = null;

    private final Minecraft mc = Minecraft.getMinecraft();

    public void enable(){
        RPC.Discord_ClearPresence();

        DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRichPresence presence = new DiscordRichPresence();

        presence.largeImageText = FaxHax.MOD_NAME + " v" + FaxHax.VERSION;
        presence.joinSecret = SECRET;
        presence.startTimestamp = START_TIME;

        final DiscordUser[] user = new DiscordUser[1];

        handlers.ready = u -> {
            user[0] = u;
            System.out.println("[FaxHax] Discord RPC Started! Welcome " + u.username);
        };
        handlers.disconnected = (code, message) -> System.out.println("[FaxHax] Disconnected with error code " + code + " and trace:\n" + message);
        handlers.joinRequest = request -> {
            if(!request.username.equals(user[0].username)){
                if(mc.player!=null) FaxMessageUtil.sendClientMessage("[DiscordRpc] " +request.username+" has requested to join!");
            }
        };

        RPC.Discord_Initialize(APP_ID,handlers,true,"");

        THREAD = new Thread(() ->{
            while (!Thread.currentThread().isInterrupted()){
                RPC.Discord_RunCallbacks();
                presence.largeImageKey = "faxhax";
                presence.details = getDiscordDetails();
                if(getDiscordState()!=null) presence.state = getDiscordState();

                RPC.Discord_UpdatePresence(presence);

                try { Thread.sleep(2 * 1000L); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        THREAD.start();
    }

    public void disable(){
        if(THREAD==null) return;
        THREAD.interrupt();
        THREAD=null;
        RPC.Discord_ClearPresence();
        RPC.Discord_Shutdown();
    }

    private String getDiscordDetails() {
        if (mc.player != null) {
            if (mc.getCurrentServerData() != null) return "playing " + mc.getCurrentServerData().serverIP;

            return "chilling in singleplayer";
        }

        return "in the main menu";
    }

    private String getDiscordState(){
        if (mc.player != null) {
            if (mc.getCurrentServerData() != null) return mc.getCurrentServerData().pingToServer + "ms | " + mc.player.getHealth() + "hp";
            return "facing " + FaxPlayerUtil.GetFacing().toString();
        }
        return null;
    }

    private FaxDiscord(){}
}
