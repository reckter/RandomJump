package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface DamageEventHandler<T extends BaseEntity> extends BaseEventHandler {

    /**
     * gets called when the victim gets Damage from the offender
     * @param victim
     * @param offender
     */
    public void onDamage(T victim, BaseEntity offender, float dmg);
}
