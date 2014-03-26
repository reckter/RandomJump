package me.reckter.Entity.Category.Matching.Logic;

import me.reckter.Entity.Category.Category;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MATCH extends BaseLogic {

    public MATCH(BaseLogic logicA) {
        super(logicA, logicA);
    }

    public MATCH(Category catA) {
        super(catA, catA);
    }


    public boolean resolve(TreeSet<Category> categories) {
        if(hasChildren) {
            return logicA.resolve(categories);
        }
        return categories.contains(catA);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        if(hasChildren) {
            return "(" + logicA.toString() + ")";
        }
        return "(" + catA + ")";
    }
}
