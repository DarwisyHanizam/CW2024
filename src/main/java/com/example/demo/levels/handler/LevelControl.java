package com.example.demo.levels.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelControl {
	private LevelAll levelAll;

	public LevelControl(LevelAll levelAll){
		this.levelAll = levelAll;
	}

	public void startGame() {
		levelAll.background.requestFocus();
		levelAll.timeline.play();
	}

	public void goToNextLevel(String levelName) {
		notifyActionListener(levelName);
	}

	public void winGame() {
		levelAll.timeline.stop();
		levelAll.levelView.showWinImage();
	}

	public void loseGame() {
		levelAll.timeline.stop();
		levelAll.levelView.showGameOverImage();
	}

	private void notifyActionListener(String levelName) {
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, levelName);
		for (ActionListener listener : levelAll.actionListeners) {
			listener.actionPerformed(event);
		}
	}

	public void addActionListener(ActionListener listener) {
		levelAll.actionListeners.add(listener);
	}
}