package com.example.demo.levels.handler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class LevelInitializer{

	private LevelAll levelAll;
	private LevelAbstract levelAbstract;

	public LevelInitializer(LevelAll levelAll, LevelAbstract levelAbstract){
		this.levelAll = levelAll;
		this.levelAbstract = levelAbstract;
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
		levelAll.background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) levelAll.user.moveUp();
				if (kc == KeyCode.DOWN) levelAll.user.moveDown();
				if (kc == KeyCode.LEFT) levelAll.user.moveLeft();
				if (kc == KeyCode.RIGHT) levelAll.user.moveRight();
				if (kc == KeyCode.SPACE) levelAll.levelMechanics.fireProjectile();
			}
		});
		levelAll.background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) levelAll.user.stop("Vertical");
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) levelAll.user.stop("Horizontal");
			}
		});
		levelAll.root.getChildren().add(levelAll.background);
	}
}