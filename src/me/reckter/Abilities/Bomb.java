package me.reckter.Abilities;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Projectile.BombProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 30.12.13.
 */
public class Bomb extends Shoot {
    public Bomb(BaseEntity caster) {
        super(caster);
    }

    public void init() {
        super.init();
        MAX_COOLDOWN = 2 * 1000;
        dmg = 70;
    }

    @Override
    public void resolve() {
        BombProjectile bomb;
        Vector2f tmp = caster.coords.copy().add(new Vector2f(caster.angle).normalise().scale(caster.size.y + 2));

        bomb = new BombProjectile(caster.level, caster, tmp);
        bomb.init();
        bomb.dmg = (dmg);

        bomb.setSize(2);
        caster.level.add(bomb);

    }
}
