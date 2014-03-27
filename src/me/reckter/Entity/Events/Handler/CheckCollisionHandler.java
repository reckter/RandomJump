package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

import java.util.ArrayList;

/**
 * Created by Hannes on 3/27/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface CheckCollisionHandler<T extends BaseEntity> extends BaseEventHandler {

	/**
	 * checks if the entity collides whith any ofthe other entities
	 * @param entity
	 * @param entities
	 */
	public void checkCollision(T entity, ArrayList<BaseEntity> entities);
}
