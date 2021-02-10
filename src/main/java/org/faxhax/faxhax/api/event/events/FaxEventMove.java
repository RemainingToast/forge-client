package org.faxhax.faxhax.api.event.events;

import net.minecraft.entity.MoverType;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxEventMove extends FaxEventCancellable {
	
	private MoverType moveType;

	public double x, y, z;

	public FaxEventMove(MoverType type, double x, double y, double z) {
		this.moveType = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setMoveType(MoverType type) {
		this.moveType = type;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public MoverType getMoveType() {
		return this.moveType;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}
}
