package me.reckter.Level;

import me.reckter.Entity.Entities.Enemy.BaseEnemy;
import me.reckter.Entity.Entities.Enemy.MergingEnemy;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by mediacenter on 28.12.13.
 */
public class TestSpaceLevel extends BaseSpaceLevel{

    protected int newShipIn;
    protected int MAX_NEW_SHIP = 4 * 1000;
    protected int newWeaponPowerUp;
    protected int MAX_NEW_WEAPON = 1 * 1000;

    public TestSpaceLevel() {
        super();
        newShipIn = 3 * 1000;
        newWeaponPowerUp = 100;
        WIDTH = 10 * 1000;
        HEIGHT = 10 * 1000;
    }

    @Override
    public void populate() {
        super.populate();
        BaseEnemy tmp = new MergingEnemy(this);
        tmp.init();
        tmp.coords = player.coords.copy().add(new Vector2f(Math.random() * 360).scale(200));
        tmp.target = player;
        add(tmp);


    }

    @Override
    public void logic(int delta) {

        newShipIn -= delta;
        if(newShipIn <= 0){
            newShipIn = MAX_NEW_SHIP;
            for(int i = 0; i <= 1  ; i++){
                BaseEnemy tmp = new MergingEnemy(this);
                tmp.init();
                tmp.coords = player.coords.copy().add(new Vector2f(Math.random() * 360).scale((float) new Random().nextGaussian() * 1500));
                tmp.target = player;
                add(tmp);
            }
        }
        super.logic(delta);
    }
}
