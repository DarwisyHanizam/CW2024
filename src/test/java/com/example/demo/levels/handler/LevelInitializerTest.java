package com.example.demo.levels.handler;

import com.example.demo.controller.Main;
import com.example.demo.levels.LevelBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LevelInitializerTest extends ApplicationTest{
	private LevelBuilder levelBuilder;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		levelBuilder = main.getController().getCurrentLevel();
	}

	@Test
	public void testInitializeScene() {
		LevelDisplay levelDisplay = levelBuilder.getLevelDisplay();

		assertEquals(5, levelDisplay.getHeartDisplay().getContainer().getChildren().size());
	}

	@Test
	public void testInitializeBackground() {
		ImageView background = levelBuilder.getBackground();

		assertTrue(background.isFocused());
		assertEquals(750, background.getFitHeight(), 0.5);
		assertEquals(1300, background.getFitWidth(), 0.5);
		assertTrue(levelBuilder.getRoot().getChildren().contains(background));
	}

}