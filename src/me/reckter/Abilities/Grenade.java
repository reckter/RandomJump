package me.reckter.Abilities;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Entity.Entities.Player;
import me.reckter.Entity.Entities.Projectile.GrenadeProjectile;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 30.12.13.
 */
public class Grenade extends Shoot {

    protected Vector2f destination;

    public Grenade(BaseEntity caster) {
        super(caster);
    }

    @Override
    public void init() {
        super.init();
        MAX_COOLDOWN = 2 * 1000;
        destination = new Vector2f(0,0);
    }

    @Override
    public void resolve() {

        GrenadeProjectile grenade;
        if(caster instanceof Player){
            Input input = caster.level.getInput();
            destination = new Vector2f(caster.level.getRealX(input.getMouseX()), caster.level.getRealY(input.getMouseY()));
        } else if(caster instanceof BaseEnemy){
            destination = ((BaseEnemy) caster).target.coords.copy();
        }

        Vector2f tmp = caster.coords.copy().add(new Vector2f(caster.angle).scale((float) caster.size.y + 2));

        grenade = new GrenadeProjectile(caster.level, caster, tmp, new Vector2f(caster.angle), 500, destination);

        grenade.init();
        grenade.dmg = dmg;

        grenade.setSize(2);
        caster.level.add(grenade);

    }
}
