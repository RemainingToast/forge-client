package org.faxhax.faxhax.api.event.events;

import net.minecraft.network.Packet;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxPacket extends FaxEventCancellable {
	private final Packet packet;

	public FaxPacket(Packet packet) {
		super();

		this.packet = packet;
	}

	public Packet getPacket() {
		return this.packet;
	}

	public static class ReceivePacket extends FaxPacket {
		public ReceivePacket(Packet packet) {
			super(packet);
		}
	}

	public static class SendPacket extends FaxPacket {
		public SendPacket(Packet packet) {
			super(packet);
		}
	}
}