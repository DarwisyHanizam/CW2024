package com.example.demo.levels.handler;

import com.example.demo.levels.LevelBuilder;
import com.example.demo.levels.LevelTemplate;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Configuration{
	private LevelBuilder levelBuilder;
	private LevelTemplate levelTemplate;

	public Configuration(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
		levelTemplate = levelBuilder.getLevelTemplate();
	}

	public Scene initializeScene() {
		initializeBackground();
		levelTemplate.initializeFriendlyUnits();
		levelBuilder.getLevelDisplay().showHeartDisplay();
		return levelBuilder.getScene();
	}

	public void initializeTimeline() {
		SceneUpdater sceneHandler = levelBuilder.getSceneUpdater();
		Timeline timeline = levelBuilder.getTimeline();
		int delay = levelBuilder.getMillisecondDelay();

		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(delay), e -> sceneHandler.updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		ImageView background = levelBuilder.getBackground();
		
		background.setFocusTraversable(true);
		background.setFitHeight(levelBuilder.getScreenHeight());
		background.setFitWidth(levelBuilder.getScreenWidth());
		levelBuilder.getUserInput().inputHandler();
		levelBuilder.getRoot().getChildren().add(background);
	}
}