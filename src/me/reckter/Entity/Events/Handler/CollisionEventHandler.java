package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface CollisionEventHandler<T extends BaseEntity> extends BaseEventHandler {

    /**
     * gets called when the victim collides with the offender
     * @param victim
     * @param offender
     */
    public void onCollsion(T victim, BaseEntity offender);
}
