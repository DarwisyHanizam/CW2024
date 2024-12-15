package com.example.demo.levels.handler;

import com.example.demo.levels.LevelBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manages the progression of the game, including starting the game, transitioning between levels, and handling win/lose conditions.
 */
public class Progression {

	private LevelBuilder levelBuilder;

	/**
	 * Constructs a new Progression instance.
	 * 
	 * @param levelBuilder the LevelBuilder instance associated with this Progression
	 */
	public Progression(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
	}

	/**
	 * Starts the game by requesting focus for the background and playing the timeline.
	 */
	public void startGame() {
		levelBuilder.getBackground().requestFocus();
		levelBuilder.getTimeline().play();
	}

	/**
	 * Transitions to the next level.
	 * 
	 * @param levelName the name of the next level
	 */
	public void goToNextLevel(String levelName) {
		String levelLocation = "com.example.demo.levels.types.";
		notifyActionListener(levelLocation + levelName);
	}

	/**
	 * Handles the win condition by stopping the timeline and showing the win image.
	 */
	public void winGame() {
		levelBuilder.getTimeline().stop();
		levelBuilder.getLevelDisplay().showWinImage();
	}

	/**
	 * Handles the lose condition by stopping the timeline and showing the game over image.
	 */
	public void loseGame() {
		levelBuilder.getTimeline().stop();
		levelBuilder.getLevelDisplay().showGameOverImage();
	}

	/**
	 * Notifies action listeners of a level change.
	 * 
	 * @param levelName the name of the next level
	 */
	private void notifyActionListener(String levelName) {
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, levelName);
		for (ActionListener listener : levelBuilder.getActionListeners()) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * Adds an action listener to the level builder.
	 * 
	 * @param listener the action listener to add
	 */
	public void addActionListener(ActionListener listener) {
		levelBuilder.getActionListeners().add(listener);
	}

}