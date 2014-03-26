package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface ExplosionEventHandler<T extends BaseEntity> extends BaseEventHandler {

    /**
     * gets called when an entity explodes(and the every tick)
     * @param entity
     */
    public void onExplosion(T entity);
}
