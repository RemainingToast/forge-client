package org.faxhax.faxhax.api.event;

import me.zero.alpine.fork.listener.Listenable;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.faxhax.faxhax.FaxHax;
import org.lwjgl.input.Keyboard;

public class FaxEventProcessor implements Listenable {

    public static FaxEventProcessor INSTANCE;
    Minecraft mc = Minecraft.getMinecraft();

    public FaxEventProcessor() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player != null) {
            FaxHax.MODULES.onUpdate();
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.isCanceled()) event.setCanceled(true);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        FaxHax.EVENTS.post(event);
        if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            FaxHax.MODULES.onRender();
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            FaxHax.MODULES.onBind(Keyboard.getEventKey());
        }
    }

    public void init(){
        FaxHax.EVENTS.subscribe(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
