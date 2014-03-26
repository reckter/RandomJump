package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Events.Handler.CollisionEventHandler;
import me.reckter.Entity.Events.Handler.JumpEventHandler;
import me.reckter.Entity.Events.Handler.TickEventHandler;
import me.reckter.Entity.Events.Priority;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;


/**
 * Created by Hannes on 3/26/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Gravity extends BaseLogic implements TickEventHandler, CollisionEventHandler, JumpEventHandler{

	Vector2f gravity;
	public Gravity(BaseLevel level) {
		super(new Matching("MOVEMENT&GRAVITY"), level);
		gravity = new Vector2f(0,1000);
	}

	/**
	 * gets called when the victim collides with the offender
	 *
	 * @param victim
	 * @param offender
	 */
	@Override
	public void onCollsion(BaseEntity victim, BaseEntity offender) {
		if(offender.categories.contains(Category.PASSABLE_PLATFORM)) {
			float victimY = victim.getHitBox().getMaxY();
			float offenderY = offender.getHitBox().getMinY();
			if(victimY >= offenderY && victimY < offenderY + offender.getHitBox().getHeight() / 10 && victim.movement.y >= 0){
				victim.movement.y = 0;
				//victim.coords.y = offender.coords.y ;//- victim.getAAHitBox().getHeight() - 1;
				//victim.coords.y += offender.getHitBox().getMinY() - (victim.getHitBox().getMinY() + victim.getHitBox().getHeight());
				victim.coords.y -= victim.getHitBox().getMaxY() - offender.getHitBox().getMinY();
				//victim.coords.y += 1;
				victim.isJumping = false;
			}
		} else if(offender.categories.contains(Category.NON_PASSABLE_PLATFORM)) {
			Shape victimHitBox = victim.getHitBox();
			Shape offenderHitBox = offender.getHitBox();
			// we need to chech for each collision side, so we check if that side of the hitbox is in the othher one, and check if we are on that side and not a corner
			//ramming from the left
			if(victimHitBox.getMaxX() > offenderHitBox.getMinX() && victimHitBox.getMaxX() < offenderHitBox.getMaxX() && victimHitBox.getMaxY() > offenderHitBox.getMinY() + 0.7f && victimHitBox.getMinY() < offenderHitBox.getMaxY() - 0.7f) {
				victim.coords.x -= victimHitBox.getMaxX() - offenderHitBox.getMinX();
			} //ramming from the right
			else if(victimHitBox.getMinX() > offenderHitBox.getMinX() && victimHitBox.getMinX() < offenderHitBox.getMaxX() && victimHitBox.getMaxY() > offenderHitBox.getMinY() + 0.7f  && victimHitBox.getMinY() < offenderHitBox.getMaxY() - 0.7f){
				victim.coords.x += offenderHitBox.getMaxX() - victimHitBox.getMinX();
			} //ramming from above
			else if(victimHitBox.getMaxY() > offenderHitBox.getMinY() && victimHitBox.getMaxY() < offenderHitBox.getMaxY() && victimHitBox.getMaxX() > offenderHitBox.getMinX() + 0.7f && victimHitBox.getMinX() < offenderHitBox.getMaxX() - 0.7f) {
				victim.coords.y -= victimHitBox.getMaxY() - offenderHitBox.getMinY();
				if(victim.movement.y > 0) {
					victim.movement.y = 0;
					victim.isJumping = false;
				}
			} //ramming from below
			else if(victimHitBox.getMinY() > offenderHitBox.getMinY() && victimHitBox.getMinY() < offenderHitBox.getMaxY() && victimHitBox.getMaxX() > offenderHitBox.getMinX() + 0.7f && victimHitBox.getMinX() < offenderHitBox.getMaxX()- 0.7f) {
				victim.coords.y += offenderHitBox.getMaxY() - victimHitBox.getMinY();
				if(victim.movement.y < 0) {
					victim.movement.y = 0;
				}
			}
		}
	}

	/**
	 * gets called every tick
	 *
	 * @param delta  the delay between the last tick and this one
	 * @param entity the entity that needs to be processed
	 */
	@Override
	public void onTick(int delta, BaseEntity entity) {
		entity.movement.add(gravity.copy().scale((float) delta / 1000));
		if(entity.movement.y > gravity.y / 1000) {
			entity.isJumping = true;
		}
	}

	/**
	 * gets called, every time an entity jumps
	 *
	 * @param entity
	 */
	@Override
	public void onJump(BaseEntity entity) {
		entity.isJumping = true;
		entity.movement.add(entity.jumpBoost);
	}

	/**
	 * gets called every tick an entity is jumping
	 *
	 * @param entity
	 */
	@Override
	public void whileJumping(BaseEntity entity) {

	}


	/**
	 * @return the Priority of this implementation
	 */
	@Override
	public Priority getPriority() {
		return Priority.MIDDLE;
	}
}
