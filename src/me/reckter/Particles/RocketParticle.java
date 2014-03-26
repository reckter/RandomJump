package me.reckter.Particles;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by me.reckter on 1/1/14.
 */
public class RocketParticle extends BaseParticle {
    public RocketParticle(BaseLevel level, BaseEntity origin) {
        this(level, origin, 1);
    }

    public RocketParticle(BaseLevel level, BaseEntity origin, float sizeScale) {
        super(level, 0, 0, new Vector2f(origin.angle).normalise().scale(-100).add(origin.movement));
        Vector2f tail = new Vector2f(origin.coords.x,origin.coords.y).add(new Vector2f(origin.angle).normalise().scale((float) -origin.size.length() * sizeScale));
        this.x = tail.x;
        this.y = tail.y;
    }

    @Override
    public void init() {
        super.init();
        movement.add(new Vector2f(Math.random() * 360).scale((float) Math.random() * 30));
	    timeToLive = (int) (new Random().nextGaussian() * 500 + 100);
    }
}
