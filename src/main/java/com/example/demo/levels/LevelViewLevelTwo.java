package com.example.demo.levels;

import com.example.demo.viewImage.ShieldImage;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 355;
	private static final int SHIELD_Y_POSITION = 175;
	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay, double yPositionOffset) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION + yPositionOffset);
	}

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		this(root, heartsToDisplay, 0);
	}

	public void addShieldToRoot() {
		shieldImage.toFront();
		root.getChildren().add(shieldImage);
	}

	public void showShield() {
		System.out.println("Show shield");
		shieldImage.showShield();
	}

	public void hideShield() {
		System.out.println("Hide shield");
		shieldImage.hideShield();
	}

}