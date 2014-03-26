package me.reckter.Level;

import me.reckter.Interface.HUD.FPSlabel;
import me.reckter.Interface.HUD.HudCooldownBar;
import me.reckter.Interface.HUD.HudLifeBar;
import me.reckter.Interface.HUD.WeaponImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 28.12.13.
 */
public class BaseSpaceLevel extends BaseLevel {


    public BaseSpaceLevel() {
        super();
        HEIGHT = 1000;
        WIDTH = 1000;
    }

    @Override
    public void populate() {

        super.populate();

        for(int x = 0; x <= WIDTH; x += 100){
            for(int y = 0; y <= WIDTH; y += 100){
                if(Math.random() <= 0.36){
                    Vector2f point = new Vector2f(x,y).add(new Vector2f(Math.random() * 360).scale((float) Math.random() * 30 + 10));
                    //add(new BackgroundParticle(this, point.x , point.y));
                }
            }
        }
    }

    @Override
    public void init() {
        super.init();
        interfaces.add(new FPSlabel(this));
        interfaces.add(new HudLifeBar(this));
        interfaces.add(new HudCooldownBar(this));
        interfaces.add(new WeaponImage(this));
    }

    @Override
    public void render(Graphics g) {

        g.setBackground(Color.black);;
        super.render(g);

        g.setColor(Color.white);
        g.draw(new Rectangle(0, 0, WIDTH, HEIGHT));
    }

}
