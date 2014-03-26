package me.reckter.Entity.Logic;

import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Events.Handler.BaseEventHandler;
import me.reckter.Level.BaseLevel;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public abstract class BaseLogic implements BaseEventHandler, Comparable<BaseLogic> {

    public Matching matching;
    BaseLevel level;

    public BaseLogic(Matching matching, BaseLevel level) {
        this.matching = matching;
        this.level = level;
    }

    @Override
    public String toString() {
        return matching.toString();
    }

    @Override
    public int compareTo(BaseLogic o) {
        return getPriority().compareTo(o.getPriority());
    }
}
