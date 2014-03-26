package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handler.DeathEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class ProjectileLogic extends BaseLogic implements TickEventHandler, DeathEventHandler {

    public ProjectileLogic(BaseLevel level) {
        super(new Matching("PROJECTILE"), level);
    }


    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        if(entity instanceof BaseProjectile) {
            ((BaseProjectile) entity).timeToLife += delta;
            if(((BaseProjectile) entity).timeToLife >= ((BaseProjectile) entity).MAX_TTL) {
                level.fire(new DeathEvent(), entity, entity);
            }
        }
    }

    /**
     * gets called when the victim died from the offender
     *
     * @param victim
     * @param offender
     */
    @Override
    public void onDeath(BaseEntity victim, BaseEntity offender) {
        victim.isAlive = false;
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.MIDDLE;
    }

}
