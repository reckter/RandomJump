package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MaximalMovement extends BaseLogic implements TickEventHandler {

    float MAX_MOVEMENT = 800;
    public MaximalMovement(BaseLevel level) {
        super(new Matching("MAX_MOVEMENT&MOVEMENT"), level);
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        if(entity.movement.lengthSquared() > MAX_MOVEMENT * MAX_MOVEMENT){
            entity.movement.normalise().scale(MAX_MOVEMENT);
        }
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.LOWEST;
    }
}
