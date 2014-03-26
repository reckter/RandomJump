package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Entities.BaseEntity;

/**
 * Created by Hannes on 3/26/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface JumpEventHandler<T extends BaseEntity> extends BaseEventHandler{

	/**
	 * gets called, every time an entity jumps
	 * @param entity
	 */
	public void onJump(T entity);

	/**
	 * gets called every tick an entity is jumping
	 * @param entity
	 */
	public void whileJumping(T entity);
}
