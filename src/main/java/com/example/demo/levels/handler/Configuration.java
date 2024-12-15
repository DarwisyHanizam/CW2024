package com.example.demo.levels.handler;

import com.example.demo.levels.LevelBuilder;
import com.example.demo.levels.LevelTemplate;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * Handles the configuration and initialization of the game level.
 * Manages the scene, background, and timeline for the game.
 */
public class Configuration{
	private LevelBuilder levelBuilder;
	private LevelTemplate levelTemplate;

	/** *
	 * Constructs a new Configuration instance.
	 * 
	 * @param levelBuilder the LevelBuilder instance associated with this Configuration
	 */
	public Configuration(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
		levelTemplate = levelBuilder.getLevelTemplate();
	}

	/**
	 * Initializes the scene for the game level.
	 * Sets up the background, friendly units, and heart display.
	 * 
	 * @return the initialized Scene
	 */
	public Scene initializeScene() {
		initializeBackground();
		levelTemplate.initializeFriendlyUnits();
		levelBuilder.getLevelDisplay().showHeartDisplay();
		return levelBuilder.getScene();
	}

	/**
	 * Initializes the timeline for the game level.
	 * Sets up the game loop with a specified delay.
	 */
	public void initializeTimeline() {
		SceneUpdater sceneHandler = levelBuilder.getSceneUpdater();
		Timeline timeline = levelBuilder.getTimeline();
		int delay = levelBuilder.getMillisecondDelay();

		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(delay), e -> sceneHandler.updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background for the game level.
	 * Sets the background image properties and adds it to the scene.
	 */
	private void initializeBackground() {
		ImageView background = levelBuilder.getBackground();
		
		background.setFocusTraversable(true);
		background.setFitHeight(levelBuilder.getScreenHeight());
		background.setFitWidth(levelBuilder.getScreenWidth());
		levelBuilder.getUserInput().inputHandler();
		levelBuilder.getRoot().getChildren().add(background);
	}

}