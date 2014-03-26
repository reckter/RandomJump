package me.reckter.Particles;


import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by me.reckter on 1/1/14.
 */
public class DeathParticle extends BaseParticle {
    public DeathParticle(BaseLevel level, BaseEntity origin) {
        super(level, origin.coords.x, origin.coords.y, origin.movement);
        double random = Math.random();
        this.movement = origin.movement.copy().add(new Vector2f((float) Math.random() * 360).scale((float) (random * random * origin.size.length()) * 20));

    }


    @Override
    public void init() {
        super.init();
	    timeToLive = (int) (new Random().nextGaussian() * 2000 + 500);
    }
}
