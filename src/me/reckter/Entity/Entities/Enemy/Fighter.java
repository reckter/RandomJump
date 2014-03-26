package me.reckter.Entity.Entities.Enemy;

import me.reckter.Entity.Category.Category;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Fighter extends BaseEnemy {
    public Fighter(BaseLevel level) {
        super(level);
    }

    @Override
    public void init() {
        super.init();
	    categories.add(Category.FOLLOWING);
        categories.add(Category.FIGHTER);
	    life = 50;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        Vector2f point = new Vector2f(angle);
        Vector2f side = new Vector2f(angle + 90);
        Vector2f neutral = new Vector2f(coords.x,coords.y);


        point.scale(size.x);
        side.scale(size.y);


        g.draw(new Circle(coords.x, coords.y, size .length() * 0.3f));

        g.draw(new Line(neutral.copy().add(side.copy().scale(0.3f)), neutral.copy().add(side.copy().scale(0.7f))));

        g.draw(new Line(neutral.copy().add(side.copy().scale(0.7f)).add(point.copy().scale(0.4f)), neutral.copy().add(side.copy().scale(0.7f)).add(point.copy().scale(-0.4f)) ));

        g.draw(new Line(neutral.copy().add(side.copy().scale(-0.3f)), neutral.copy().add(side.copy().scale(-0.7f))));

        g.draw(new Line(neutral.copy().add(side.copy().scale(-0.7f)).add(point.copy().scale(0.4f)), neutral.copy().add(side.copy().scale(-0.7f)).add(point.copy().scale(-0.4f)) ));

	    g.setColor(Color.blue);
	    g.draw(new Line(neutral, neutral.copy().add(new Vector2f(angle).scale(size.y))));

	    g.setColor(Color.orange);
	    g.draw(new Line(neutral, neutral.copy().add(movement.copy().normalise().scale(size.y))));

	    g.setColor(Color.green);
	    g.draw(new Line(neutral, neutral.copy().add(new Vector2f(target.coords.copy().add(coords.copy().scale(-1)).getTheta()).normalise().scale(size.y))));

	    super.render(g);
    }
}
