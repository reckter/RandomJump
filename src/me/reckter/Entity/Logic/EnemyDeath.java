package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Player;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import me.reckter.Entity.Events.Handler.DeathEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EnemyDeath extends BaseLogic implements DeathEventHandler {
    public EnemyDeath(BaseLevel level) {
        super(new Matching("ENEMY&STANDART_SCORE"), level);
    }

    /**
     * gets called when the victim died from the offender
     *
     * @param victim
     * @param offender
     */
    @Override
    public void onDeath(BaseEntity victim, BaseEntity offender) {
        if (offender instanceof BaseProjectile) {
            if(((BaseProjectile) offender).origin instanceof Player) {
                level.getScore().addScore(10);
            }
        }
    }

    /**
     * @return the Priority of this implementation
     */
    @Override
    public Priority getPriority() {
        return Priority.MIDDLE;
    }
}
