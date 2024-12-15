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

/**
 * Builds and manages the level in the game.
 */
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

	/** * Constructs a new LevelBuilder instance.
	 * 
	 * @param backgroundImageName the name of the background image
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param playerInitialHealth the initial health of the player
	 */
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

	/**
	 * Adds an enemy unit to the level.
	 *
	 * @param enemy the enemy unit to add
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Checks if the user is destroyed.
	 *
	 * @return true if the user is destroyed, false otherwise
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}


	/** * Gets the collision processor.
	 * 
	 * @return the collision processor
	 */
	public CollisionProcessor getCollisionProcessor() {
		return collisionProcessor;
	}

	/**
	 * Gets the progression handler.
	 *
	 * @return the progression handler
	 */
	public Progression getProgression() {
		return progression;
	}

	/**
	 * Gets the configuration handler.
	 *
	 * @return the configuration handler
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Gets the projectile factory.
	 *
	 * @return the projectile factory
	 */
	public ProjectileFactory getProjectileFactory() {
		return projectileFactory;
	}

	/**
	 * Gets the scene updater.
	 *
	 * @return the scene updater
	 */
	public SceneUpdater getSceneUpdater() {
		return sceneUpdater;
	}

	/**
	 * Gets the level template.
	 *
	 * @return the level template
	 */
	public LevelTemplate getLevelTemplate() {
		return levelTemplate;
	}

	/**
	 * Gets the user input handler.
	 *
	 * @return the user input handler
	 */
	public UserInput getUserInput() {
		return userInput;
	}

	/**
	 * Gets the list of action listeners.
	 *
	 * @return the list of action listeners
	 */
	public List<ActionListener> getActionListeners() {
		return actionListeners;
	}

	/**
	 * Gets the list of friendly units.
	 *
	 * @return the list of friendly units
	 */
	public List<ActiveActorDestructible> getFriendlyUnits() {
		return friendlyUnits;
	}

	/**
	 * Gets the list of enemy units.
	 *
	 * @return the list of enemy units
	 */
	public List<ActiveActorDestructible> getEnemyUnits() {
		return enemyUnits;
	}

	/**
	 * Gets the list of user projectiles.
	 *
	 * @return the list of user projectiles
	 */
	public List<ActiveActorDestructible> getUserProjectiles() {
		return userProjectiles;
	}

	/**
	 * Gets the list of enemy projectiles.
	 *
	 * @return the list of enemy projectiles
	 */
	public List<ActiveActorDestructible> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	/**
	 * Gets the millisecond delay.
	 *
	 * @return the millisecond delay
	 */
	public int getMillisecondDelay() {
		return MILLISECOND_DELAY;
	}

	/**
	 * Gets the maximum Y position for enemies.
	 *
	 * @return the maximum Y position for enemies
	 */
	public double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Gets the screen height.
	 *
	 * @return the screen height
	 */
	public double getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Gets the screen width.
	 *
	 * @return the screen width
	 */
	public double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Gets the root group.
	 *
	 * @return the root group
	 */
	public Group getRoot() {
		return root;
	}

	/**
	 * Gets the timeline.
	 *
	 * @return the timeline
	 */
	public Timeline getTimeline() {
		return timeline;
	}

	/**
	 * Gets the user plane.
	 *
	 * @return the user plane
	 */
	public UserPlane getUser() {
		return user;
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Gets the background image view.
	 *
	 * @return the background image view
	 */
	public ImageView getBackground() {
		return background;
	}

	/**
	 * Gets the current number of enemies.
	 *
	 * @return the current number of enemies
	 */
	public int getCurrentNumberOfEnemies() {
		return currentNumberOfEnemies;
	}

	/**
	 * Sets the current number of enemies.
	 *
	 * @param num the current number of enemies
	 */
	public void setCurrentNumberOfEnemies(int num) {
		currentNumberOfEnemies = num;
	}

	/**
	 * Gets the level display.
	 *
	 * @return the level display
	 */
	public LevelDisplay getLevelDisplay() {
		return levelDisplay;
	}

}