package com.example.demo.levels.handler;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.friendly.UserPlane;
import com.example.demo.levels.LevelView;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelAll {

	protected LevelCollision levelCollision;
	public LevelControl levelControl;
	public LevelInitializer levelInitializer;
	protected LevelMechanics levelMechanics;
	public LevelScene levelScene;
	protected LevelAbstract levelAbstract;

	protected final List<ActionListener> actionListeners;
	protected final List<ActiveActorDestructible> friendlyUnits;
	protected final List<ActiveActorDestructible> enemyUnits;
	protected final List<ActiveActorDestructible> userProjectiles;
	protected final List<ActiveActorDestructible> enemyProjectiles;

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double enemyMaximumYPosition;
	protected final double screenHeight;
	protected final double screenWidth;

	protected final Group root;
	protected final Timeline timeline;
	protected final UserPlane user;
	protected final Scene scene;
	protected final ImageView background;

	protected int currentNumberOfEnemies;
	protected LevelView levelView;

	public LevelAll(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth){
		this.levelCollision = new LevelCollision(this);
		this.levelControl = new LevelControl(this);
		//this.levelInitializer = new LevelInitializer(this);
		this.levelMechanics = new LevelMechanics(this);
		//this.levelScene = new LevelScene(this);

		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.actionListeners = new ArrayList<>();
		this.friendlyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		background.setOpacity(0.5);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.currentNumberOfEnemies = 0;

		//this.levelView = instantiateLevelView();
		//initializeTimeline();
		//friendlyUnits.add(user);
	}

	protected void initializeLevelAbstract(LevelAbstract levelAbstract){
		setLevelAbstract(levelAbstract);
		System.out.println("LevelAll: " + this);
		System.out.println("LevelAbstract: " + levelAbstract);
		
		this.levelInitializer = new LevelInitializer(this, levelAbstract);
		this.levelScene = new LevelScene(this, levelAbstract);
		this.levelView = levelAbstract.instantiateLevelView();
		levelInitializer.initializeTimeline();
		friendlyUnits.add(user);
	}

	protected void setLevelAbstract(LevelAbstract levelAbstract){
		this.levelAbstract = levelAbstract;
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	protected int getDelay(){
		return MILLISECOND_DELAY;
	}
}