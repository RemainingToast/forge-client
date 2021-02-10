package org.faxhax.faxhax.api.event.events;
import net.minecraft.client.entity.AbstractClientPlayer;
import org.faxhax.faxhax.api.event.FaxEventCancellable;

public class FaxRenderName extends FaxEventCancellable {

    public AbstractClientPlayer Entity;
    public double X;
    public double Y;
    public double Z;
    public String Name;
    public double DistanceSq;
    
    public FaxRenderName(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq) {
		super();

		Entity = entityIn;
        x = X;
        y = Y;
        z = Z;
        Name = name;
        DistanceSq = distanceSq;
	}

}