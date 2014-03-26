package me.reckter.Entity.Entities.Projectile;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Hannes on 2/25/14.
 *
 * @author Hannes Güdelhöfer
 */
public class GrenadeProjectile extends BaseProjectile {

    public int EXPLOSION_TIME;
    public int FUSE_TIME;
    public Vector2f destination;

    public float MAX_EXPLOSION_SIZE;

    public GrenadeProjectile(BaseLevel level) {
        super(level);
    }

    public GrenadeProjectile(BaseLevel level, BaseEntity origin) {
        super(level, origin);
    }

    public GrenadeProjectile(BaseLevel level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed, Vector2f destination) {
        super(level, origin, coord, movement, speed);
        this.destination = destination;
    }

    /**
    * checks if the grenade is exploding
    * @return
            */
    public boolean isExploding(){
        return timeToLife >= FUSE_TIME;
    }


	@Override
	public Rectangle getAAHitBox() {
		return new Rectangle(coords.x - size.y, coords.y - size.y, size.y * 2, size.y * 2);
	}

    @Override
    public Shape getHitBox() {
        return new Circle(coords.x,coords.y,size.y);
    }

    @Override
    public void init() {

        FUSE_TIME = (int) (1000 * (coords.copy().add(destination.copy().negate()).length()/ movement.length()));

        super.init();
	    categories.remove(Category.NORMAL_PROJECTILE_COLLISION);

        categories.add(Category.GRENADE);

        EXPLOSION_TIME = 100;
        dmg = 50;

        MAX_EXPLOSION_SIZE = 70;

        FUSE_TIME = (int) (1000 * (coords.copy().add(destination.copy().negate()).length()/ movement.length()));

        MAX_TTL = EXPLOSION_TIME + FUSE_TIME;
	    doesOriginDmg = true;

    }

    /*
    @Override
    public void render(Graphics g) {
        if(isExploding()){
            g.setColor(Color.red);
            g.fill(new Circle(x,y,size));
        } else {
            super.render(g);
        }
    }
    */

    @Override
    public boolean checkCollision(BaseEntity with) {
        return isExploding() && super.checkCollision(with);
    }
}
