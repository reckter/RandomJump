package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Platform.BasePlatform;
import me.reckter.Entity.Events.CollisionEvent;
import me.reckter.Entity.Events.Handler.CheckCollisionHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

import java.util.ArrayList;

/**
 * Created by Hannes on 3/27/14.
 *
 * @author Hannes Güdelhöfer
 */
public class PlatformLogic extends BaseLogic implements CheckCollisionHandler<BasePlatform> {


	public PlatformLogic(BaseLevel level) {
		super(new Matching("PLATFORM"), level);
	}

	/**
	 * checks if the entity collides whith any ofthe other entities
	 *
	 * @param entity
	 * @param entities
	 */
	@Override
	public void checkCollision(BasePlatform entity, ArrayList<BaseEntity> entities) {
		if(!entity.isAlive()) {
			return;
		}
		for(BaseEntity entityTmp: entities){
			if(entity != entityTmp) {
				if(entityTmp.isAlive && entityTmp.checkCollision(entity)){
					entity.level.fire(new CollisionEvent() ,entity, entityTmp);
					entity.level.fire(new CollisionEvent(), entityTmp, entity);
				}
			}
		}
	}


	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return null;
	}

}
