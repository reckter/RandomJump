package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.Handler.AcceleratingEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import me.reckter.Particles.RocketParticle;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Movement extends BaseLogic implements TickEventHandler, AcceleratingEventHandler {
    public Movement(BaseLevel level) {
        super(new Matching("MOVEMENT"), level);
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, BaseEntity entity) {
        entity.coords.add(entity.movement.copy().scale((float) delta / 1000));
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

    /**
     * gets called when the given entity accelerates
     *
     * @param entity
     */
    @Override
    public void onAcceleration(BaseEntity entity, float acceleration, int delta) {
	    entity.movement.add(new Vector2f(entity.angle).scale(acceleration).scale((float) delta / 1000));
        level.add(new RocketParticle(level, entity));
    }
}
