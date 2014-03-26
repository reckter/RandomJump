package me.reckter.Entity.Entities.Enemy;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseEnemy extends BaseEntity {

    public BaseEntity target;

    public BaseEnemy(BaseLevel level) {
        super(level);
    }


    @Override
    public void init() {
        super.init();
        categories.add(Category.ENEMY);
        setSize(13);
    }
}
