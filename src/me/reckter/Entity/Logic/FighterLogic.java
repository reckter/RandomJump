package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Entity.Entities.Enemy.Fighter;
import me.reckter.Entity.Events.DamageEvent;
import me.reckter.Entity.Events.Handler.CollisionEventHandler;
import me.reckter.Entity.Events.Handler.DeathEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class FighterLogic extends BaseLogic implements DeathEventHandler, CollisionEventHandler {
    public FighterLogic(BaseLevel level) {
        super(new Matching("MOVEMENT&FIGHTER"), level);
    }

    /**
     * gets called when the victim died from the offender
     *
     * @param victim
     * @param offender
     */
    @Override
    public void onDeath(BaseEntity victim, BaseEntity offender) {
        BaseEnemy tmp = new Fighter(level);
        tmp.init();
        tmp.coords = level.getPlayer().coords.copy().add(new Vector2f(Math.random() * 360).scale((float) Math.random() * 1000 + 1000));
        tmp.target = level.getPlayer();
        level.add(tmp);
    }

    /**
     * gets called when the victim collides with the offender
     *
     * @param victim
     * @param offender
     */
    @Override
    public void onCollsion(BaseEntity victim, BaseEntity offender) {
        level.fire(new DamageEvent(10), offender, victim);
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

}
