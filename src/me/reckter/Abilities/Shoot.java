package me.reckter.Abilities;

import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Projectile.BaseProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by mediacenter on 28.12.13.
 */
public class Shoot extends BaseAbility {
    public Shoot(BaseEntity caster) {
        super(caster);
    }

    @Override
    public void init() {
        super.init();

        soundEngine.addSound("pew");
        MAX_COOLDOWN = 3 * 1000;
        MAX_TTL = 0;
    }

    @Override
    public boolean cast() {
        if(super.cast()){
            resolve();
            //soundEngine.playSoundAt("pew", caster.getX(), caster.getY(), 0.1f, 0.1f);
            return true;
        }
        return false;
    }

    @Override
    public void resolve() {

        Vector2f tmp = caster.coords.copy().add(new Vector2f(caster.angle).scale((float) caster.size.y + 2));
        BaseProjectile projectile = new BaseProjectile(caster.level, caster, tmp, new Vector2f(caster.angle), 300);
        projectile.init();
        projectile.dmg = dmg;

        caster.level.add(projectile);

    }

    @Override
    public void logic(int delta) {
        cooldown -= delta;
    }
}
