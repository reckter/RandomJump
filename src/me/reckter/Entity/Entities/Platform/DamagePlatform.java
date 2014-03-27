package me.reckter.Entity.Entities.Platform;

import me.reckter.Entity.Category.Category;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/27/14.
 *
 * @author Hannes Güdelhöfer
 */
public class DamagePlatform extends BasePlatform {
	public DamagePlatform(BaseLevel level, Vector2f coords, Vector2f size) {
		super(level, coords, size);
	}

	@Override
	public void init() {
		super.init();
		categories.add(Category.DAMAGE_PLATFORM);
	}
}
