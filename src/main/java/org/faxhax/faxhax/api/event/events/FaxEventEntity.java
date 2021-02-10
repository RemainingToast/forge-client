package org.faxhax.faxhax.api.event.events;

import net.minecraft.entity.Entity;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxEventEntity extends FaxEventCancellable {
	private Entity entity;

	public FaxEventEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public static class FaxEventColision extends FaxEventEntity {
		private double x, y, z;

		public FaxEventColision(Entity entity, double x, double y, double z) {
			super(entity);

			this.x = x;
			this.y = y;
			this.z = z;
		}

		public void setX(double x) {
			this.x = x;
		}

		public void setY(double y) {
			this.y = y;
		}

		public void setZ(double x) {
			this.z = z;
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
}