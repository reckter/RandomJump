package me.reckter.Entity;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.*;
import me.reckter.Entity.Events.Handler.*;
import me.reckter.Entity.Logic.BaseLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class LogicHandler {

    HashMap<BaseLogic, ArrayList<BaseEntity>> logics;

    EntityHandler entities;

    public LogicHandler(EntityHandler entityHandler) {
        entities = entityHandler;
        logics = new HashMap<BaseLogic, ArrayList<BaseEntity>>();
    }


    public void logic(int delta) {
        for(BaseLogic logic: new ArrayList<BaseLogic>(logics.keySet())) {
            if(logic instanceof TickEventHandler) {
                for(BaseEntity entity: logics.get(logic)) {
                   ((TickEventHandler) logic).onTick(delta, entity);
                }
            }
        }
    }


    public void fireEvent(BaseEvent event, BaseEntity victim, BaseEntity offender) {
        if(event instanceof DamageEvent){
            for(BaseLogic logic: logics.keySet()) {
                if(logic instanceof DamageEventHandler && victim.matches(logic.matching)) {
                    ((DamageEventHandler) logic).onDamage(victim, offender, ((DamageEvent) event).dmg);
                }
            }
        } else if(event instanceof DeathEvent){
            for(BaseLogic logic: logics.keySet()) {
                if(logic instanceof DeathEventHandler && victim.matches(logic.matching)) {
                    ((DeathEventHandler) logic).onDeath(victim, offender);
                }
            }
        } else if(event instanceof CollisionEvent){
            for(BaseLogic logic: logics.keySet()) {
                if(logic instanceof CollisionEventHandler && victim.matches(logic.matching)) {
                    ((CollisionEventHandler) logic).onCollsion(victim, offender);
                }
            }
        } else if(event instanceof AcceleratingEvent){
            for(BaseLogic logic: logics.keySet()) {
                if(logic instanceof AcceleratingEventHandler && victim.matches(logic.matching)) {
                    ((AcceleratingEventHandler) logic).onAcceleration(victim, ((AcceleratingEvent) event).acceleration, ((AcceleratingEvent) event).delta);
                }
            }
        } else if(event instanceof ExplosionEvent){
            for(BaseLogic logic: logics.keySet()) {
                if(logic instanceof ExplosionEventHandler && victim.matches(logic.matching)) {
                    ((ExplosionEventHandler) logic).onExplosion(victim);
                }
            }
        } else if(event instanceof JumpEvent){
	        for(BaseLogic logic: logics.keySet()) {
		        if(logic instanceof JumpEventHandler && victim.matches(logic.matching)) {
			        ((JumpEventHandler) logic).onJump(victim);
		        }
	        }
        }
    }

    public void updateLogics() {
        for(BaseLogic logic: logics.keySet()) {
            logics.get(logic).clear();
            for(BaseEntity entiy: entities.entities) {
                if(entiy.matches(logic.matching)) {
                    logics.get(logic).add(entiy);
                }
            }
        }
    }

    public void fireCollsionEvent(BaseEntity victim, BaseEntity offender) {
        fireEvent(new CollisionEvent(), victim, offender);
    }

    public void fireDamageEvent(BaseEntity victim, BaseEntity offender,int dmg) {
        fireEvent(new DamageEvent(dmg), victim, offender);
    }

    public void fireDeathEvent(BaseEntity victim, BaseEntity offender) {
        fireEvent(new DeathEvent(), victim, offender);
    }

    public void fireAccelerateEvent(BaseEntity victim) {
        fireEvent(new DeathEvent(), victim, victim);
    }


    public void add(BaseLogic logic) {
        logics.put(logic, new ArrayList<BaseEntity>());
        updateLogics();
    }


    public void del(BaseLogic logic) {
        logics.remove(logic);
    }

    private class LogicComparator implements Comparator<BaseEventHandler> {
        @Override
        public int compare(BaseEventHandler o1, BaseEventHandler o2) {
            return o1.getPriority().compareTo(o2.getPriority());
        }
    }
}
