package me.reckter.Interface;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 30.12.13.
 */
public class LifeBar extends BaseInterface {


    double health;
    double maxHealth;
	double healthDisplayed;

    protected BaseEntity on;

    public LifeBar(BaseLevel level, BaseEntity on) {
        super(level);
        this.on = on;

        height = 2;
        width = (int) (on.size.length() * 1.8f);
	    maxHealth = on.life;
    }


    @Override
    public boolean isAlive() {
        return on.isAlive;
    }


    @Override
    public void logic(int delta) {

        health = on.life;
        width = (int) (on.size.length() * 1.8f);

        x = (int) (on.coords.x - width / 2);
        y = (int) (on.coords.y - (on.size.length() + 3));
	    healthDisplayed += (health - healthDisplayed) / (300 / delta);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.white);
        g.fill(new Rectangle(x , y , width , height));

        g.setColor(Color.red);
        g.draw(new Rectangle(x, y, (float) (width * (healthDisplayed / maxHealth)), height));
        g.fill(new Rectangle(x, y, (float) ((health / maxHealth) * (double) width), height));
    }
}
