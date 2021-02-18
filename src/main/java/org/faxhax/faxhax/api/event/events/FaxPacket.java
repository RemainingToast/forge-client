package org.faxhax.faxhax.api.event.events;

import net.minecraft.network.Packet;
import org.faxhax.faxhax.api.event.FaxEvent;

public class FaxPacket extends FaxEvent {
	private final Packet packet;

	public FaxPacket(Packet packet) {
		super();
		this.packet = packet;
	}

	public Packet getPacket() {
		return this.packet;
	}

	public static class Receive extends FaxPacket {

		public Receive(Packet packet) {
			super(packet);
		}
	}

	public static class Send extends FaxPacket {
		public Send(Packet packet) {
			super(packet);
		}
	}

	public static class PostReceive extends FaxPacket {
		public PostReceive(Packet packet) {
			super(packet);
		}
	}

	public static class PostSend extends FaxPacket {
		public PostSend(Packet packet) {
			super(packet);
		}
	}
}