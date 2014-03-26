package me.reckter.Level;

import me.reckter.Engine;
import me.reckter.Entity.Entities.BaseEntity;
import me.reckter.Entity.Entities.Player;
import me.reckter.Entity.EntityHandler;
import me.reckter.Entity.Events.BaseEvent;
import me.reckter.Entity.Logic.*;
import me.reckter.Interface.BaseInterface;
import me.reckter.Interface.HUD.Score;
import me.reckter.Interface.InterfaceHandler;
import me.reckter.Log;
import me.reckter.Particles.BaseParticle;
import me.reckter.Particles.ParticleHandler;
import me.reckter.Sound.SoundEngine;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

/**
 * Created by mediacenter on 25.12.13.
 */
public class BaseLevel {



    protected int camX;
    protected int camY;

    protected int fps;

    protected Input input; //workaroung till the real input is implemented

    protected SoundEngine soundEngine;

    public EntityHandler entities;

    protected BaseLevel nextLevel;

	public ParticleHandler particles;

    public InterfaceHandler interfaces;

    protected Player player; // the player

    protected Score score;

	float zoom = 1;

    public int HEIGHT = 400;
    public int WIDTH = 640;


    public BaseLevel(){

        this.entities = new EntityHandler(this);

        this.interfaces = new InterfaceHandler();

        this.particles = new ParticleHandler(this);

        this.score = new Score(this);

        this.nextLevel = null;

        this.soundEngine = new SoundEngine();
    }

    /**
     * populates the Level
     */
    public void populate(){
        Log.info("Populting level...");
        player = new Player(this);
        player.init();

        player.setX(HEIGHT / 2);
        player.setY(WIDTH / 2);

        add(player);
    }

    /**
     * gets called bevor every logic tick
     * @param input
     */
    public void processInput(Input input){
        player.processInput(input);
	    if(input.isKeyDown(Input.KEY_I)) {
		    zoom *= 1.01f;
	    }
	    if(input.isKeyDown(Input.KEY_K)) {
		    zoom /= 1.01f;
	    }
    }

    /**
     * gets called when Level is initialized
     */
    public void init(){
        Log.info("initializing level...");

        interfaces.add(score);
        entities.updateEntityList();
        entities.add(new Movement(this));
        entities.add(new Boundary(this));
        entities.add(new PlayerLogic(this));
        entities.add(new EnemyDeath(this));
        entities.add(new FighterLogic(this));
        entities.add(new MaximalMovement(this));
        entities.add(new BasicLogic(this));
        entities.add(new ProjectileLogic(this));
        entities.add(new GrenadeLogic(this));
	    entities.add(new MergingLogic(this));
	    entities.add(new FollowingLogic(this));
	    entities.add(new Gravity(this));
    }

    /**
     * ads an entity to the level
     * @param entity
     */
    public void add(BaseEntity entity){
        entities.add(entity);
    }

    /**
     * adds an interface
     * @param face the interface to be added
     */
    public void add(BaseInterface face){
        interfaces.add(face);
    }

    public void fire(BaseEvent event, BaseEntity victim, BaseEntity offender) {
        entities.logicHandler.fireEvent(event, victim, offender);
    }

    public void fire(BaseEvent event, BaseEntity victim) {
        entities.logicHandler.fireEvent(event, victim, victim);
    }


    /**
     * adds a particle
     * @param particle the interface to be added
     */
    public void add(BaseParticle particle){
        particles.add(particle);
    }


    /**
     * delets an entity
     * @param entity the entity to be deleted
     */
    public void del(BaseEntity entity){
        entities.del(entity);
    }



    /**
     * delets a particle
     * @param particle the entity to be deleted
     */
    public void del(BaseParticle particle){
        particles.del(particle);
    }

    /**
     * delets an Interface
     * @param face the particle to be deleted
     */
    public void del(BaseInterface face){
        interfaces.del(face);
    }


    /**
     * gets called every logic tick
     * @param delta the time in ms since the last logic tick
     */
    public void logic(int delta) {

        interfaces.logic(delta);

        particles.logic(delta);

        entities.logic(delta);
    }

    /**
     * gets called evey render
     * @param g the graphics object on which should be drawn
     */
    public void render(Graphics g){

        if(player != null){
            camX = (int) - (player.coords.x - (Engine.SCREEN_WIDTH / zoom ) / 2);
            camY = (int) - (player.coords.y - (Engine.SCREEN_HEIGHT / zoom) / 2);
        }
	    g.scale(zoom, zoom);
	    g.translate(camX, camY);

        particles.render(g);

        entities.render(g);

        interfaces.render(g);

    }

    /**
     * returns the X position on the field (from an x position on the screen)
     * @param x
     * @return
     */
    public float getRealX(float x){
        return x + ( - camX);
    }

    /**
     * returns the X position on the Screen (from an x position on the field)
     * @param x
     * @return
     */
    public float getScreenX(float x){
        return x - ( - camX);
    }

    /**
     * returns the Y position on the field (from an y position on the screen)
     * @param y
     * @return
     */
    public float getRealY(float y){
        return y + ( - camY);
    }

    /**
     * returns the Y position on the Screen (from an y position on the field)
     * @param y
     * @return
     */
    public float getScreenY(float y){
        return y - ( - camY);
    }



    public ArrayList<BaseEntity> getEntities() {
        return entities.entities;
    }

    public void setEntities(ArrayList<BaseEntity> entities) {
        this.entities.entities = entities;
    }

    public ParticleHandler getParticles() {
        return particles;
    }

    public void setParticles(ParticleHandler particles) {
        this.particles = particles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCamX() {
        return camX;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public BaseLevel getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(BaseLevel nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public SoundEngine getSoundEngine() {
        return soundEngine;
    }

    public void setSoundEngine(SoundEngine soundEngine) {
        this.soundEngine = soundEngine;
    }

}

