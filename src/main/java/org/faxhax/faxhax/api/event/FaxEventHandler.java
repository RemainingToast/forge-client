package org.faxhax.faxhax.api.event;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.math.MathHelper;
import org.faxhax.faxhax.FaxHax;
import org.faxhax.faxhax.api.event.events.FaxPacket;

import java.util.Arrays;

public class FaxEventHandler implements Listenable {
	public static FaxEventHandler INSTANCE;

	static final float[] ticks = new float[20];

	private long lastTick;
	private int nextIndex = 0;

	@EventHandler
	private final Listener<FaxPacket.ReceivePacket> receivePacket = new Listener<>(event -> {
		if (event.getPacket() instanceof SPacketTimeUpdate) {
			INSTANCE.updateTime();
		}
	});

	public FaxEventHandler() {
		FaxHax.EVENTS.subscribe(receivePacket);
		resetTick();
	}

	public float getTickRate() {
		float num_ticks = 0.0f;
		float sum_ticks = 0.0f;

		for (float tick : ticks) {
			if (tick > 0.0f) {
				sum_ticks += tick;
				num_ticks += 1.0f; 
			}
		}

		return MathHelper.clamp(sum_ticks / num_ticks, 0.0f, 20.0f);
	}

	public void resetTick() {
		this.nextIndex       = 0;
		this.lastTick = -1L;

		Arrays.fill(ticks, 0.0f);
	}

	public void updateTime() {
		if (this.lastTick != -1L) {
			float time = (float) (System.currentTimeMillis() - this.lastTick) / 1000.0f;
			ticks[(this.nextIndex % ticks.length)] = MathHelper.clamp(20.0f / time, 0.0f, 20.0f);
			
			this.nextIndex += 1;
		}

		this.lastTick = System.currentTimeMillis();
	}
}