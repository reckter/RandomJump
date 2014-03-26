package me.reckter.Interface;

import me.reckter.Abilities.BaseAbility;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 05.01.14.
 */
public class CooldownBar extends BaseInterface {


    protected BaseEntity on;
    protected float cd;
    public CooldownBar(BaseLevel level, BaseEntity on) {
        super(level);
        this.on = on;

        height = 2;
        width = (int) (on.size.length() * 1.8f);
    }


    @Override
    public boolean isAlive() {
        return on.isAlive();
    }


    @Override
    public void logic(int delta) {

        BaseAbility ability = on.getAbilities().get(on.getWeapon());

        cd = (float) ability.getCooldown() / (float) ability.getMAX_COOLDOWN();
        if(cd < 0){
            cd = 0;
        }

        width = (int) (on.size.length() * 1.8f);

        x = (int) (on.coords.x - width / 2);
        y = (int) (on.coords.y - (on.size.length() + 5));

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        if(cd == 0){
            return;
        }
        g.setColor(Color.white);
        // g.draw(new SHx, y, (float) (width * (healthDisplayed / maxHealth)), height);
        g.fill(new Rectangle(x, y, (float) (cd * (double) width), height));
        g.setColor(Color.white);
    }
}
