package me.reckter.Entity.Category.Matching.Logic;

import me.reckter.Entity.Category.Category;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class XOR extends BaseLogic {

    public XOR(Category catA, Category catB) {
        super(catA, catB);
    }

    public XOR(BaseLogic logicA, BaseLogic logicB) {
        super(logicA, logicB);
    }

    @Override
    public boolean resolve(TreeSet<Category> categories) {
        if(hasChildren) {
            return logicA.resolve(categories) != logicB.resolve(categories);
        }
        return categories.contains(catA) != categories.contains(catB);
    }

    @Override
    public String getName() {
        return "^";
    }
}
