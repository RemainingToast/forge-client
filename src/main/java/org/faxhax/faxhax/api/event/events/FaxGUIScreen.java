package org.faxhax.faxhax.api.event.events;

import net.minecraft.client.gui.GuiScreen;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxGUIScreen extends FaxEventCancellable {
	private final GuiScreen guiscreen;

	public FaxGUIScreen(GuiScreen screen) {
		super();

		guiscreen = screen;
	}

	public GuiScreen getGuiscreen() {
		return guiscreen;
	}
}