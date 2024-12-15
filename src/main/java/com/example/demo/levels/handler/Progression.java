package com.example.demo.levels.handler;

import com.example.demo.levels.LevelBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Progression {
	private LevelBuilder levelBuilder;

	public Progression(LevelBuilder levelBuilder){
		this.levelBuilder = levelBuilder;
	}

	public void startGame() {
		levelBuilder.getBackground().requestFocus();
		levelBuilder.getTimeline().play();
	}

	public void goToNextLevel(String levelName) {
		notifyActionListener(levelName);
	}

	public void winGame() {
		levelBuilder.getTimeline().stop();
		levelBuilder.getLevelDisplay().showWinImage();
	}

	public void loseGame() {
		levelBuilder.getTimeline().stop();
		levelBuilder.getLevelDisplay().showGameOverImage();
	}

	private void notifyActionListener(String levelName) {
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, levelName);
		for (ActionListener listener : levelBuilder.getActionListeners()) {
			listener.actionPerformed(event);
		}
	}

	public void addActionListener(ActionListener listener) {
		levelBuilder.getActionListeners().add(listener);
	}
}