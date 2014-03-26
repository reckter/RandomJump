package me.reckter.Entity.Category.Matching.Logic;

import me.reckter.Entity.Category.Category;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class OR extends BaseLogic{

    public OR(BaseLogic logicA, BaseLogic logicB) {
        super(logicA, logicB);
    }

    public OR(Category catA, Category catB) {
        super(catA, catB);
    }
    @Override
    public boolean resolve(TreeSet<Category> categories) {
        if (hasChildren) {
            return logicA.resolve(categories) || logicB.resolve(categories);
        }

        return categories.contains(catA) || categories.contains(catB);
    }

    @Override
    public String getName() {
        return "|";
    }
}
