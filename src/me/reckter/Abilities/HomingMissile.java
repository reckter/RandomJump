package me.reckter.Abilities;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

/**
 * Created by mediacenter on 30.12.13.
 */
public class HomingMissile extends Shoot {
    protected float detectorRange = 15;
    public HomingMissile(BaseEntity caster) {
        super(caster);
    }

    @Override
    public void init() {
        super.init();
        MAX_COOLDOWN = 3 * 1000;
        dmg = 20;
    }

    @Override
    public void resolve() {
        BaseEntity target = caster;
        if(caster instanceof Player){

            ArrayList<BaseEntity> entities = caster.level.getEntities();
            Input input = caster.level.getInput();

            Vector2f mouse = new Vector2f(caster.level.getRealX(input.getMouseX()), caster.level.getRealY(input.getMouseY()));
            Vector2f entityVector;
            Vector2f nearestVector;

            for(BaseEntity entity: entities){
                entityVector = new Vector2f(entity.coords.x, entity.coords.y);
                nearestVector = new Vector2f(target.coords.x, target.coords.y);

                if(mouse.distanceSquared(entityVector) <= detectorRange * detectorRange && mouse.distanceSquared(entityVector) < mouse.distanceSquared(nearestVector)){
                    target = entity;
                }
            }
        }
        /*
        if(caster instanceof BaseEnemy){
            target = ((BaseEnemy) caster).getTarget();
        }

        Vector2f tmp = new Vector2f(caster.coords.x,caster.coords.y).add(new Vector2f(caster.getAngle()).scale((float) caster.getSize() + 2));
        BaseProjectile projectile = new HomingMissileProjectile(caster.level, caster, tmp.x, tmp.y, new Vector2f(caster.getAngle()), target);
        projectile.init();
        projectile.setDmg(dmg);

        caster.level.add(projectile);
        */
    }
}
