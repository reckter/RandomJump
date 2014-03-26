package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Entity.Entities.Enemy.MergingEnemy;
import me.reckter.Entity.Entities.Player;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handler.CollisionEventHandler;
import me.reckter.Entity.Events.Handler.DeathEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hannes on 3/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MergingLogic extends BaseLogic implements TickEventHandler<MergingEnemy>, CollisionEventHandler<MergingEnemy>, DeathEventHandler<MergingEnemy> {
	public MergingLogic(BaseLevel level) {
		super(new Matching("MOVEMENT&MERGING"), level);
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param victim
	 * @param offender
	 */
	@Override
	public void onCollsion(MergingEnemy victim, BaseEntity offender) {
		if(victim.isAlive() && offender.isAlive()) {
			if(offender instanceof MergingEnemy && victim.value == ((MergingEnemy) offender).value) {
				victim.level.fire(new DeathEvent(), offender, victim);
				victim.value *= 2;
				victim.life += offender.life;
				victim.life *= 0.666666;
				victim.movement.add(offender.movement);
			}
		}
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, MergingEnemy entity) {
		ArrayList<BaseEntity> entities = entity.level.getEntities();
		BaseEntity bestTareget = entity.target;
		double bestDistance = 999999;
		if(bestTareget != null) {
			bestTareget.coords.distance(entity.coords);
		}
		for(BaseEntity tmpEntity: entities) {
			if(tmpEntity.coords.distance(entity.coords) > 1000 || tmpEntity == entity) {
				continue;
			}
			if(bestTareget == null || ((((tmpEntity instanceof MergingEnemy && ((MergingEnemy) tmpEntity).value == entity.value ) || tmpEntity instanceof Player)) && tmpEntity.coords.distance(entity.coords) < bestDistance)) {
				bestTareget = tmpEntity;
				bestDistance = bestTareget.coords.distance(entity.coords);
			}
		}
		entity.target = bestTareget;
	}


	/**
	 * gets called when the victim died from the offender
	 *
	 * @param victim
	 * @param offender
	 */
	@Override
	public void onDeath(MergingEnemy victim, BaseEntity offender) {
		if (offender instanceof BaseProjectile) {
			if(((BaseProjectile) offender).origin instanceof Player) {
				level.getScore().addScore(victim.value);
			}
		}
		BaseEnemy tmp = new MergingEnemy(level);
		tmp.init();
		tmp.coords = level.getPlayer().coords.copy().add(new Vector2f(Math.random() * 360).scale((float) new Random().nextGaussian() * 500));
		tmp.target = level.getPlayer();
		level.add(tmp);
	}
	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.HIGH;
	}

}
