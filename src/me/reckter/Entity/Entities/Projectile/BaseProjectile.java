package me.reckter.Entity.Entities.Projectile;

import me.reckter.Abilities.AbilityHandler;
import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;
import me.reckter.Modifier.ModifierHandler;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseProjectile extends BaseEntity {

    public BaseEntity origin;

    public int timeToLife;
    public int MAX_TTL = 3 * 1000;

	public boolean doesOriginDmg = false;

    public float dmg;

    public BaseProjectile(BaseLevel level) {
        super(level);
        coords = new Vector2f(0,0);
        size = new Vector2f(0,0);
        movement = new Vector2f(0,0);
    }

    public BaseProjectile(BaseLevel level, BaseEntity origin) {
        this(level);
        this.origin = origin;
    }

    public BaseProjectile(BaseLevel level, BaseEntity origin, Vector2f coord, Vector2f movement, float speed){
        this(level);

        this.movement = movement;
        this.movement = origin.movement.copy().add(movement.normalise().scale(speed));
        this.coords = coord;
        this.origin = origin;
    }



	@Override
    public void init() {

        modifiers = new ModifierHandler();
        isAlive = true;
        life = 100;

        abilities = new AbilityHandler();

        categories = new TreeSet<Category>();

        categories.add(Category.MOVEMENT);
        categories.add(Category.GRAVITY);
        categories.add(Category.MAX_MOVEMENT);

        categories.add(Category.PROJECTILE);
		categories.add(Category.NORMAL_PROJECTILE_COLLISION);
        setHeight(2);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fill(new Circle(coords.x, coords.y, size.y));
        super.render(g);
    }
}
