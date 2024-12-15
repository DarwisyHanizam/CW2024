package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.friendly.UserPlane;
import com.example.demo.levels.handler.CollisionProcessor;
import com.example.demo.levels.handler.Configuration;
import com.example.demo.levels.handler.UserInput;
import com.example.demo.levels.handler.LevelDisplay;
import com.example.demo.levels.handler.Progression;
import com.example.demo.levels.handler.ProjectileFactory;
import com.example.demo.levels.handler.SceneUpdater;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelBuilder {

	private final CollisionProcessor collisionProcessor;
	private final Configuration configuration;
	private final UserInput userInput;
	private final LevelTemplate levelTemplate;
	private final LevelDisplay levelDisplay;
	private final Progression progression;
	private final ProjectileFactory projectileFactory;
	private final SceneUpdater sceneUpdater;

	private final List<ActionListener> actionListeners;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> enemyProjectiles;
	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> userProjectiles;

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double enemyMaximumYPosition;
	private final double screenHeight;
	private final double screenWidth;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private int currentNumberOfEnemies;

	public LevelBuilder(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth){
		this.levelTemplate = (LevelTemplate) this;
		this.collisionProcessor = new CollisionProcessor(this);
		this.progression = new Progression(this);
		this.configuration = new Configuration(this);
		this.projectileFactory = new ProjectileFactory(this);
		this.sceneUpdater = new SceneUpdater(this);
		this.userInput = new UserInput(this);

		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.actionListeners = new ArrayList<>();
		this.friendlyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		String backgroundFolder = "/com/example/demo/images/backgrounds/";
		String image = backgroundFolder + backgroundImageName;
		this.background = new ImageView(new Image(getClass().getResource(image).toExternalForm()));
		background.setOpacity(0.5);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.currentNumberOfEnemies = 0;

		this.levelDisplay = levelTemplate.instantiateLevelDisplay();
		configuration.initializeTimeline();
		friendlyUnits.add(user);
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}


	public CollisionProcessor getCollisionProcessor() {
		return collisionProcessor;
	}

	public Progression getProgression() {
		return progression;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public ProjectileFactory getProjectileFactory() {
		return projectileFactory;
	}

	public SceneUpdater getSceneUpdater() {
		return sceneUpdater;
	}

	public LevelTemplate getLevelTemplate() {
		return levelTemplate;
	}

	public UserInput getUserInput() {
		return userInput;
	}

	public List<ActionListener> getActionListeners() {
		return actionListeners;
	}

	public List<ActiveActorDestructible> getFriendlyUnits() {
		return friendlyUnits;
	}

	public List<ActiveActorDestructible> getEnemyUnits() {
		return enemyUnits;
	}

	public List<ActiveActorDestructible> getUserProjectiles() {
		return userProjectiles;
	}

	public List<ActiveActorDestructible> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	public int getMillisecondDelay() {
		return MILLISECOND_DELAY;
	}

	public double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public Group getRoot() {
		return root;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public UserPlane getUser() {
		return user;
	}

	public Scene getScene() {
		return scene;
	}

	public ImageView getBackground() {
		return background;
	}

	public int getCurrentNumberOfEnemies() {
		return currentNumberOfEnemies;
	}

	public void setCurrentNumberOfEnemies(int num) {
		currentNumberOfEnemies = num;
	}

	public LevelDisplay getLevelDisplay() {
		return levelDisplay;
	}

}