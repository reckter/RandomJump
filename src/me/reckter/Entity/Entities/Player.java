package me.reckter.Entity.Entities;

import me.reckter.Abilities.*;
import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Events.JumpEvent;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created bcoords.y Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Player extends BaseEntity {

	public boolean rightPressed;
	public boolean leftPressed;

    public Player(BaseLevel level) {
        super(level);
    }

    @Override
    public void init() {
        super.init();
	    categories.add(Category.PLAYER);
	    categories.add(Category.GRAVITY);
        categories.remove(Category.MAX_MOVEMENT);

        abilities.add("laser", new Laser(this, 200));
        abilities.add("shoot", new Shoot(this));
        abilities.add("grenade", new Grenade(this));
        abilities.add("bomb", new Bomb(this));
        abilities.add("missile", new HomingMissile(this));

        abilities.get("shoot").setMAX_COOLDOWN(500);
        abilities.get("shoot").setDmg(10);

        abilities.get("grenade").setDmg(40);
        abilities.get("grenade").setMAX_COOLDOWN(2 * 1000);

	    weapon = "grenade";
	    size = new Vector2f(20,20);
    }

    /**
     * gets called bevore evercoords.y logic tick so the input can be handled
     * @param input
     */
    public void processInput(Input input){

	    if(input.isKeyDown(Input.KEY_A)) {
		    leftPressed = true;
	    } else {
		    leftPressed = false;
	    }

	    if(input.isKeyDown(Input.KEY_D)) {
		    rightPressed = true;
	    } else {
		    rightPressed = false;
	    }

        if(input.isKeyDown(Input.KEY_SPACE) && !isJumping){
	        level.fire(new JumpEvent(), this);
        }

        if(input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
            if(abilities.cast(this.weapon)){
                //  testSound.play();
            }

        }

    }
    
    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        //g.fill(new Circle(coords.x, coords.y, size));

        Vector2f directionPoint = new Vector2f(angle);
        Vector2f sitePoint = new Vector2f(angle + 90);
		Vector2f neutral = coords.copy().add(size.copy().scale(0.5f));

        directionPoint.scale(size.x).scale(0.5f);
        sitePoint.scale(size.y / 2).scale(0.5f);

        Vector2f siteA = neutral.copy().add(sitePoint.copy().scale(-1)).add(directionPoint.copy().scale(-1));
        Vector2f siteB = neutral.copy().add(sitePoint.copy().scale(1)).add(directionPoint.copy().scale(-1));
        Vector2f pointer = neutral.copy().add(directionPoint);

        g.draw(new Line(siteA,siteB));
        g.draw(new Line(siteA,pointer));
        g.draw(new Line(pointer, siteB));

        super.render(g);
    }
}
