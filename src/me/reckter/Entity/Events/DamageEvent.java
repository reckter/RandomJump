package me.reckter.Entity.Events;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class DamageEvent extends BaseEvent {

    public float dmg;

    public DamageEvent(float dmg) {
        this.dmg = dmg;
    }
}
