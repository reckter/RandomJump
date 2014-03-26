package me.reckter.Entity.Entities.Projectile;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/18/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BombProjectile extends GrenadeProjectile {
	public BombProjectile(BaseLevel level) {
		super(level);
	}

	public BombProjectile(BaseLevel level, BaseEntity origin) {
		super(level, origin);
	}

	public BombProjectile(BaseLevel level, BaseEntity origin, Vector2f coord) {
		super(level, origin, coord, new Vector2f(0,0), 0, coord);
	}

	@Override
	public void init() {
		super.init();
		categories.remove(Category.MOVEMENT);
		MAX_EXPLOSION_SIZE = 200;
		FUSE_TIME = 1300;
		MAX_TTL = EXPLOSION_TIME + FUSE_TIME;

		doesOriginDmg = true;
	}
}
