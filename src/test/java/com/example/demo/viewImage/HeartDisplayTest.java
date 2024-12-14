package com.example.demo.viewImage;
import com.example.demo.controller.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HeartDisplayTest extends ApplicationTest {

	private HeartDisplay heartDisplay;
	private HBox container;
	private int initialHealth = 5;
	private Main main = new Main();

	@Override
	public void start(Stage stage) throws Exception {
		main.start(stage);
		heartDisplay = new HeartDisplay(0, 0, initialHealth);
		container = heartDisplay.getContainer();
	}

	@Test
	public void testRemoveHearts() {
		Platform.runLater(() -> {
			try {
				heartDisplay.removeHeart();
				assertFalse(container.getChildren().isEmpty());
				assertTrue(container.getChildren().size() == initialHealth - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}