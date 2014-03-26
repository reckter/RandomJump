package me.reckter.Entity.Category.Matching.Logic;

import me.reckter.Entity.Category.Category;

import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public abstract class BaseLogic {

    protected boolean hasChildren;
    protected BaseLogic(Category catA, Category catB) {
        this.catA = catA;
        this.catB = catB;
        hasChildren = false;
    }

    protected BaseLogic() {
        hasChildren = true;
    }

    protected BaseLogic(BaseLogic logicA, BaseLogic logicB) {
        this.logicA = logicA;
        this.logicB = logicB;
        hasChildren = true;
    }

    protected Category catA;
    protected Category catB;

    protected BaseLogic logicA;
    protected BaseLogic logicB;

    abstract public boolean resolve(TreeSet<Category> categories);
    abstract public String getName();

    public String toString() {
        if(hasChildren) {
            return "(" + logicA.toString() + getName() + logicB.toString() + ")";
        }
        return "(" + catA + getName() + catB + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof BaseLogic && o.getClass().equals(this.getClass())) {
                if(hasChildren && ((BaseLogic) o).hasChildren) {
                    return ((BaseLogic) o).logicA.equals(logicA) && ((BaseLogic) o).logicB.equals(logicB);
                } else if(!hasChildren && !((BaseLogic) o).hasChildren) {
                    return ((BaseLogic) o).catA == catA && ((BaseLogic) o).catB == catB;
                } else {
                    return false;
                }
            }
        return false;
    }
}
