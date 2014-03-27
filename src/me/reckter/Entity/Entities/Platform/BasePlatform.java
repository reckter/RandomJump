package me.reckter.Entity.Entities.Platform;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import java.util.TreeSet;

/**
 * Created by Hannes on 3/26/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasePlatform extends BaseEntity {
	public BasePlatform(BaseLevel level, Vector2f coords, Vector2f size) {
		super(level);
		this.coords = coords;
		this.size = size;
	}

	@Override
	public Shape getHitBox() {
		return getAAHitBox();
	}

	@Override
	public Rectangle getAAHitBox() {
		return new Rectangle(coords.x, coords.y , size.x , size.y );
	}

	@Override
	public void init() {
		categories = new TreeSet<Category>();
		isAlive = true;
		categories.add(Category.PLATFORM);
		categories.add(Category.NEEDS_COLLISION_CHECKING);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fill(getAAHitBox());
		super.render(g);
	}
}
