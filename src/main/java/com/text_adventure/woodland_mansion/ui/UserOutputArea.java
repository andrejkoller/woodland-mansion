package com.text_adventure.woodland_mansion.ui;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class UserOutputArea extends Pane {
	private final Font gameOutputAreaFont = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 28);
	private final TextArea output;

	public UserOutputArea() {
		this.output = new TextArea();
		output.setPrefHeight(350);
		output.setPrefWidth(1200);
		output.setStyle("-fx-control-inner-background: #000000;");
		output.setFont(this.gameOutputAreaFont);
		output.setCursor(Cursor.DEFAULT);
		output.setEditable(true);
		output.setMouseTransparent(true);
		output.setFocusTraversable(false);
		output.setWrapText(true);
		getChildren().addAll(this.output);
	}

	public void appendTextOutput(String text) {
		output.appendText(text + "\n");
	}
}
