package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface AcceleratingEventHandler<T extends BaseEntity> extends BaseEventHandler {

    /**
     * gets called when the given entity accelerates
     * @param entity
     */
    public void onAcceleration(T entity, float acceleration, int delta);
}
