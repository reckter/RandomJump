package me.reckter.Particles;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by Hannes on 3/19/14.
 *
 * @author Hannes Güdelhöfer
 */
public class ExplosionParticle extends BaseParticle {
	public ExplosionParticle(BaseLevel level, BaseEntity on, double maxEplisionSize) {
		super(level, on.coords.x, on.coords.y, on.movement);
		movement = new Vector2f(Math.random() * 360).scale((float) (new Random().nextGaussian() * (maxEplisionSize / (on.size.y + 2.5)) * 10));
	}



	@Override
	public void init() {
		super.init();
		timeToLive = (int) (new Random().nextGaussian() * 2000 + 500);
	}
}
