package com.example.demo.levels;

import com.example.demo.ShieldImage;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 355;
	private static final int SHIELD_Y_POSITION = 175;
	private final Group root;
	private final ShieldImage shieldImage;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		System.out.println("3rd");
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
	}
	
	public void addShieldToRoot() {
		System.out.println("when is this");
		root.getChildren().add(shieldImage);
		shieldImage.toFront();
	}
	
	public void showShield() {
		System.out.println("did show");
		shieldImage.showShield();
	}

	public void hideShield() {
		System.out.println("didn't show");
		shieldImage.hideShield();
	}

}
