package me.reckter.Entity.Events;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class AcceleratingEvent extends BaseEvent {

	public float acceleration;
	public int delta;

	public AcceleratingEvent(float acceleration, int delta) {
		this.acceleration = acceleration;
		this.delta = delta;
	}
}
