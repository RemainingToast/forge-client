package org.faxhax.faxhax.api.event.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxEventRender extends FaxEventCancellable {
	private final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
	private final Tessellator tessellator;
	private final Vec3d       renderPos;

	public FaxEventRender(Tessellator tessellator, Vec3d pos) {
		super();

		this.tessellator = tessellator;
		this.renderPos  = pos;
	}

	public Tessellator getTessellator() {
		return this.tessellator;
	}

	public Vec3d getRenderPos() {
		return renderPos;
	}

	public BufferBuilder getBufferBuilder() {
		return this.tessellator.getBuffer();
	}

	public void setTranslation(Vec3d pos) {
		getBufferBuilder().setTranslation(- pos.x, - pos.y, - pos.z);
	}

	public void resetTranslation() {
		setTranslation(renderPos);
	}

	public double getScreenWidth() {
		return res.getScaledWidth_double();
	  }
	  
	  public double getScreenHeight() {
		return res.getScaledHeight_double();
	  }
}