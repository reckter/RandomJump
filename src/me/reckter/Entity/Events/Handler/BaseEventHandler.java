package me.reckter.Entity.Events.Handler;

import me.reckter.Entity.Events.Priority;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public interface BaseEventHandler {


    /**
     *
     * @return the Priority of this implementation
     */
    public Priority getPriority();
}
