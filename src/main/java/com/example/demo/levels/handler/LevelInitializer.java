package com.example.demo.levels.handler;

import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class LevelInitializer{
	private LevelAll levelAll;
	private LevelAbstract levelAbstract;

	public LevelInitializer(LevelAll levelAll){
		this.levelAll = levelAll;
		this.levelAbstract = (LevelAbstract) levelAll;
	}

	public Scene initializeScene() {
		initializeBackground();
		levelAbstract.initializeFriendlyUnits();
		levelAll.levelView.showHeartDisplay();
		return levelAll.scene;
	}

	void initializeTimeline() {
		levelAll.timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(levelAll.getDelay()), e -> levelAll.levelScene.updateScene());
		levelAll.timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		levelAll.background.setFocusTraversable(true);
		levelAll.background.setFitHeight(levelAll.screenHeight);
		levelAll.background.setFitWidth(levelAll.screenWidth);
		levelAll.userInput.inputHandler();
		levelAll.root.getChildren().add(levelAll.background);
	}
}