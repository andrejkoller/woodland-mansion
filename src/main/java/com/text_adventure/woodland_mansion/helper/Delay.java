package com.text_adventure.woodland_mansion.helper;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

public class Delay extends Pane {
	public Delay() {
	}
	
	public void setDelay(long millis, Runnable continueation) {
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(millis);
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(e -> continueation.run());
		new Thread(sleeper).start();
	}
}
