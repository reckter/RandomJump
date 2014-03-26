package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Entity.Events.AcceleratingEvent;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 3/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class FollowingLogic extends BaseLogic implements TickEventHandler<BaseEnemy> {
	public FollowingLogic( BaseLevel level) {
		super(new Matching("FOLLOWING"), level);
	}


	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEnemy entity) {
		if(entity.target == null || !entity.target.isAlive()) {
			entity.target = null;
			return;
		}
		Vector2f target = entity.target.coords.copy().add(entity.coords.copy().negate()).normalise().scale(1.1f);
		entity.angle =  target.add(entity.movement.copy().normalise().negate()).getTheta();
		if((Math.abs(entity.movement.getTheta() - entity.angle) > 20 || entity.movement.length() < 300)) {
			level.fire(new AcceleratingEvent(100, delta), entity);
		}
	}


	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
