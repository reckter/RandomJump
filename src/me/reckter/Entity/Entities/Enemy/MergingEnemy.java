package me.reckter.Entity.Entities.Enemy;

import me.reckter.Entity.Category.Category;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

/**
 * Created by Hannes on 3/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MergingEnemy extends BaseEnemy{

	public int value;
	public MergingEnemy(BaseLevel level) {
		super(level);
	}

	@Override
	public void init() {
		super.init();
		categories.add(Category.FOLLOWING);
		categories.add(Category.MERGING);
		value = 2;
		life = value * 20;
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawString("" + value, coords.x, coords.y);





		if(target != null) {
			g.setColor(Color.green);
			g.draw(new Line(coords, target.coords));
		}
	}
}
