package me.reckter.Entity.Entities;

import me.reckter.Abilities.AbilityHandler;
import me.reckter.Abilities.BaseAbility;
import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Interface.LifeBar;
import me.reckter.Level.BaseLevel;
import me.reckter.Modifier.BaseModifier;
import me.reckter.Modifier.ModifierHandler;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseEntity {
    public Vector2f coords;
    public Vector2f size;
    public Vector2f movement;
	public Vector2f jumpBoost;

    public double angle;

    public int immuneToDamage;

    public float life;
    public boolean isAlive;
    public BaseLevel level;

	public boolean isJumping;

    public AbilityHandler abilities;
    public ModifierHandler modifiers;
    public String weapon;

    public BaseEntity(BaseLevel level) {
        this.level = level;
    }

    public TreeSet<Category> categories;

    public void init() {
	    isJumping = false;
        coords = new Vector2f(0,0);
        size = new Vector2f(0,0);
        movement = new Vector2f(0,0);
	    jumpBoost = new Vector2f(0, -550);

        modifiers = new ModifierHandler();
        isAlive = true;
        life = 100;

	    level.add(new LifeBar(level, this));
        abilities = new AbilityHandler();

        categories = new TreeSet<Category>();
        categories.add(Category.MOVEMENT);
        //categories.add(Category.BOUNCES);
        categories.add(Category.MAX_MOVEMENT);
        categories.add(Category.DAMAGABLE);
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.draw(getAAHitBox());
    }


    public Rectangle getAAHitBox(){
        return new Rectangle(coords.x, coords.y, size.x , size.y);
    }

    public Shape getHitBox(){
        return getAAHitBox();
    }

    public boolean checkCollision(BaseEntity with) {
        if(getAAHitBox().intersects(with.getAAHitBox())){
            return getHitBox().intersects(with.getHitBox());
        }
        return false;
    }

    public void addCategory(Category category) {
        categories.add(category);
	    level.entities.logicHandler.updateLogics();
    }

    public void delCategory(Category category) {
        categories.remove(category);
	    level.entities.logicHandler.updateLogics();
    }

    public boolean matches(Matching matching) {
        return matching.resolve(categories);
    }


    /**
     * adds a modifier
     * @param modifier
     */
    public void add(BaseModifier modifier){
        modifiers.add(modifier);
    }

    /**
     * removes a modifier
     * @param modifier
     */
    public void del(BaseModifier modifier){
        modifiers.del(modifier);
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setX(float x) {
        coords.x = x;
    }

    public void setY(float y) {
        coords.y = y;
    }

    public void setSize(float size){
        this.size.set(1,1).normalise().scale(size);
    }

    public void setWidth(float witdh){
        this.size.x = witdh;
    }

    public void setHeight(float height){
        this.size.y = height;
    }

    public AbilityHandler getAbilities() {
        return abilities;
    }

    public String getWeapon() {
        return weapon;
    }

	public BaseAbility getActiveAbility() {
		return abilities.get(this.weapon);
	}

}
