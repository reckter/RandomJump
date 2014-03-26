package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Entities.Projectile.GrenadeProjectile;
import me.reckter.Entity.Events.DamageEvent;
import me.reckter.Entity.Events.ExplosionEvent;
import me.reckter.Entity.Events.Handler.CollisionEventHandler;
import me.reckter.Entity.Events.Handler.ExplosionEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import me.reckter.Particles.BaseParticle;
import me.reckter.Particles.ExplosionParticle;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public class GrenadeLogic extends BaseLogic implements TickEventHandler<GrenadeProjectile>, ExplosionEventHandler<GrenadeProjectile>, CollisionEventHandler<GrenadeProjectile>{
    public GrenadeLogic(BaseLevel level) {
        super(new Matching("GRENADE"), level);
    }


    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, GrenadeProjectile entity) {
        if(entity.isExploding()){
            level.fire(new ExplosionEvent(), entity);
        }
    }

    /**
     * gets called when an entity explodes(and the every tick)
     *
     * @param entity
     */
    @Override
    public void onExplosion(GrenadeProjectile entity) {
        entity.setHeight((((float) entity.timeToLife - (float)entity.FUSE_TIME) / (float)entity.EXPLOSION_TIME) * (float)entity.MAX_EXPLOSION_SIZE);
	    entity.delCategory(Category.MOVEMENT);
        for(int i = 0; i < (entity.MAX_EXPLOSION_SIZE / (entity.size.y + 3)) * 10; i++){ //TODO particle number

            BaseParticle particle = new ExplosionParticle(entity.level, entity, entity.MAX_EXPLOSION_SIZE);
		            //new BaseParticle(level, entity.coords.x, entity.coords.y, new Vector2f(Math.random() * 360).scale((float) Math.random() * (entity.MAX_EXPLOSION_SIZE / (entity.size.y + 3)) * 10));
            //int ttl = (int) Math.random() * 100;
            //particle.setTimeToLive(ttl * ttl);
	        particle.init();
            level.add(particle);
        }
    }

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param victim
	 * @param offender
	 */
	@Override
	public void onCollsion(GrenadeProjectile victim, BaseEntity offender) {
		if((!victim.isExploding()) && offender != victim.origin) {
			victim.timeToLife = victim.FUSE_TIME;
		}
		if(victim.timeToLife >= 300 && !(offender instanceof BaseProjectile) && (victim.doesOriginDmg || offender != victim.origin)) {
			level.fire(new DamageEvent(victim.dmg), offender, victim);
		}
	}

}
