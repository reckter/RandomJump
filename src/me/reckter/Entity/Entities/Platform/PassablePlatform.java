package me.reckter.Entity.Entities.Platform;

import me.reckter.Entity.Category.Category;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/26/14.
 *
 * @author Hannes Güdelhöfer
 */
public class PassablePlatform extends BasePlatform {
	public PassablePlatform(BaseLevel level, Vector2f coords, Vector2f size) {
		super(level, coords, size);
	}

	@Override
	public void init() {
		super.init();
		categories.add(Category.PASSABLE_PLATFORM);
	}


	@Override
	public void render(Graphics g) {
		super.render(g);

		g.setColor(Color.red);
		Rectangle tmp = getAAHitBox();
		tmp.setHeight(getAAHitBox().getHeight() / 10);
		g.fill(tmp);
	}
}
