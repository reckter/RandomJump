package me.reckter.Level;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Platform.NonPassablePlatform;
import me.reckter.Entity.Entities.Platform.PassablePlatform;
import me.reckter.Interface.HUD.FPSlabel;
import me.reckter.Interface.HUD.HudCooldownBar;
import me.reckter.Interface.HUD.HudLifeBar;
import me.reckter.Interface.HUD.WeaponImage;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/26/14.
 *
 * @author Hannes Güdelhöfer
 */
public class JumpLevel extends BaseLevel {


	/**
	 * populates the Level
	 */
	@Override
	public void populate() {
		super.populate();
		BaseEntity tmp = new PassablePlatform(this, new Vector2f(100, 450), new Vector2f(2000, 10));
		tmp.init();
		add(tmp);

		tmp = new PassablePlatform(this, new Vector2f(400, 350), new Vector2f(500, 10));
		tmp.init();
		add(tmp);

		tmp = new NonPassablePlatform(this, new Vector2f(500, 250), new Vector2f(100, 150));
		tmp.init();
		add(tmp);

		tmp = new NonPassablePlatform(this, new Vector2f(1000, 250), new Vector2f(100, 150));
		tmp.init();
		add(tmp);
	}

	/**
	 * gets called when Level is initialized
	 */
	@Override
	public void init() {
		super.init();
		interfaces.add(new FPSlabel(this));
		interfaces.add(new HudLifeBar(this));
		interfaces.add(new HudCooldownBar(this));
		interfaces.add(new WeaponImage(this));
	}
}
