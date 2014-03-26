package me.reckter.Entity.Category.Matching.Logic;

import me.reckter.Entity.Category.Category;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class NOT extends BaseLogic {

    public NOT(BaseLogic logicA) {
        super(logicA, logicA);
    }

    public NOT(Category catA) {
        super(catA, catA);
    }

    @Override
    public boolean resolve(TreeSet<Category> categories) {
        if(hasChildren) {
            return !logicA.resolve(categories);
        }
        return !categories.contains(catA);
    }

    @Override
    public String getName() {
        return "!";
    }

    @Override
    public String toString() {
        if(hasChildren) {
            return getName() + "(" + logicA.toString() + ")";
        }
        return getName() + catA;
    }
}
