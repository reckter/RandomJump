package me.reckter.Entity;

import me.reckter.Engine;
import me.reckter.Entity.Category.Matching.Matching;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Logic.BaseLogic;
import me.reckter.Level.BaseLevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class EntityHandler {
    public ArrayList<BaseEntity> entities;
    ArrayList<BaseEntity> entitiesToRemove;
    ArrayList<BaseEntity> entitiesToAdd;

    public LogicHandler logicHandler;

    BaseLevel level;
    public EntityHandler(BaseLevel level) {
        entities = new ArrayList<BaseEntity>();
        entitiesToRemove = new ArrayList<BaseEntity>();
        entitiesToAdd = new ArrayList<BaseEntity>();
        logicHandler = new LogicHandler(this);
        this.level = level;
    }

    public ArrayList<BaseEntity> getEntitiesWichMatch(Matching matching) {
        ArrayList<BaseEntity> ret = new ArrayList<BaseEntity>();
        for(BaseEntity entity: entities) {
            if(matching.resolve(entity.categories)) {
                ret.add(entity);
            }
        }
        return ret;
    }

    public void updateEntityList(){
        entities.addAll(entitiesToAdd);

        entities.removeAll(entitiesToRemove);
        if(!entitiesToRemove.isEmpty() || !entitiesToAdd.isEmpty()) {
            logicHandler.updateLogics();
        }
        entitiesToAdd = new ArrayList<BaseEntity>();
        entitiesToRemove = new ArrayList<BaseEntity>();
    }


    public void logic(int delta) {
        updateEntityList();

        logicHandler.logic(delta);

        //collision checking
	    /*
        BaseEntity entityA, entityB;
        for(int i = 0; i < entities.size(); i++){
            entityA = entities.get(i);
            if(!entityA.isAlive()){
                continue;
            }

            for(int j = i + 1; j < entities.size(); j++){
                entityB = entities.get(j);
                if(entityA == entityB){
                    entitiesToRemove.add(entityB);
                } else if(entityA.isAlive && entityA.checkCollision(entityB)){

                    logicHandler.fireCollsionEvent(entityA, entityB);
                    logicHandler.fireCollsionEvent(entityB, entityA);

                }
            }
        }
        */


        //removing all dead entities
        for(BaseEntity entity: entities){
            if(!entity.isAlive){
                entitiesToRemove.add(entity);
            }
        }

    }

    public void render(Graphics g) {
        Rectangle screen = new Rectangle(level.getRealX(0), level.getRealY(0),Engine.SCREEN_WIDTH, Engine.SCREEN_HEIGHT);
        for(BaseEntity entity: entities) {
            //if(entity.getAAHitBox().intersects(screen)) {
                entity.render(g);
            //}
        }
    }

    public void add(BaseLogic logic) {
        logicHandler.add(logic);
    }

    public void del(BaseLogic logic) {
        logicHandler.del(logic);
    }

    public void add(BaseEntity entity){
        entitiesToAdd.add(entity);
    }

    public void del(BaseEntity entity){
        entity.isAlive = false;
        entitiesToRemove.add(entity);
    }
}
