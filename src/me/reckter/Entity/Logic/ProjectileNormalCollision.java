package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.DamageEvent;
import me.reckter.Entity.Events.Handler.CollisionEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 3/19/14.
 *
 * @author Hannes Güdelhöfer
 */
public class ProjectileNormalCollision extends BaseLogic implements CollisionEventHandler<BaseProjectile> {
	public ProjectileNormalCollision(BaseLevel level) {
		super(new Matching("PROJECTILE&NORMAL_PROJECTILE_COLLISION"), level);
	}


	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param victim
	 * @param offender
	 */
	@Override
	public void onCollsion(BaseProjectile victim, BaseEntity offender) {
		if(!(offender instanceof BaseProjectile) && (victim.doesOriginDmg || offender != victim.origin)) {
			level.fire(new DamageEvent(((BaseProjectile) victim).dmg), offender, victim);
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
