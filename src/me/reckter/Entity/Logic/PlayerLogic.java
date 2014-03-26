package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.Player;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class PlayerLogic extends BaseLogic implements TickEventHandler<Player> {


    protected final float MOMENTUM = 1000;

    public PlayerLogic(BaseLevel level) {
        super(new Matching("MOVEMENT&PLAYER"), level);
    }

    /**
     * gets called every tick
     *
     * @param delta  the delay between the last tick and this one
     * @param entity the entity that needs to be processed
     */
    @Override
    public void onTick(int delta, Player entity) {
        if(entity.rightPressed) {
	        entity.movement.x = MOMENTUM;
        }
	    if(entity.leftPressed) {
		    entity.movement.x = -MOMENTUM;
	    }
	    if(entity.leftPressed == entity.rightPressed) {
		    entity.movement.x = 0;
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
