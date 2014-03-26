package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.DeathEvent;
import me.reckter.Entity.Events.Handler.DamageEventHandler;
import me.reckter.Entity.Events.Handler.DeathEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Interface.DamageText;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BasicLogic extends BaseLogic implements DeathEventHandler, DamageEventHandler, TickEventHandler {
    public BasicLogic(BaseLevel level) {
        super(new Matching("DAMAGABLE"), level);
    }

    /**
     * gets called when the victim gets Damage from the offender
     *
     * @param victim
     * @param offender
     * @param dmg
     */
    @Override
    public void onDamage(BaseEntity victim, BaseEntity offender, float dmg) {
        if(victim.immuneToDamage <= 0){
            victim.immuneToDamage = 400;
            victim.life -= dmg;
            level.add(new DamageText(level, (int) dmg, victim));
            if(victim.life <= 0){
                level.fire(new DeathEvent(), victim, offender);
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
        victim.abilities.cancelAll();
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        entity.immuneToDamage -= delta;
        entity.abilities.logic(delta);
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.HIGHEST;
    }

}
