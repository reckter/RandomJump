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
public class Boundary extends BaseLogic implements TickEventHandler {
    public Boundary(BaseLevel level) {
        super(new Matching("BOUNCES"), level);
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        if(entity.coords.x < 0) {
            entity.coords.x = 0;
            entity.movement.x = -entity.movement.x;
        } else if(entity.coords.x > level.WIDTH) {
            entity.coords.x = level.WIDTH;
            entity.movement.x = -entity.movement.x;
        }
        if(entity.coords.y < 0) {
            entity.coords.y = 0;
            entity.movement.y = -entity.movement.y;
        } else if(entity.coords.y > level.HEIGHT) {
            entity.coords.y = level.WIDTH;
            entity.movement.y = -entity.movement.y;
        }
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.HIGHEST;
    }
}
