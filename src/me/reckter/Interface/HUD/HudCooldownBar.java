package me.reckter.Interface.HUD;

import me.reckter.Abilities.BaseAbility;
import me.reckter.Engine;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by mediacenter on 05.01.14.
 */
public class HudCooldownBar extends BaseHudItem {

    protected float cd;
    public HudCooldownBar(BaseLevel engine) {
        super(engine);

        height = 4;
        width = 300;

        cd = 0;

        fixedX = Engine.SCREEN_WIDTH / 2 - width / 2;
        fixedY = Engine.SCREEN_HEIGHT - (height + 20);
    }


    @Override
    public void logic(int delta) {

        super.logic(delta);

        BaseAbility ability = level.getPlayer().getActiveAbility();

	    if(ability == null) {
		    return;
	    }

        cd = (float) ability.getCooldown() / (float) ability.getMAX_COOLDOWN();
        if(cd < 0){
            cd = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        if(cd == 0){
            return;
        }
        g.setColor(Color.white);
        // g.draw(new SHx, y, (float) (width * (healthDisplayed / maxHealth)), height);
        g.fill(new Rectangle(x, y, cd * (float) width, height));
    }
}
