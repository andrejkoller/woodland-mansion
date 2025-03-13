package com.text_adventure.woodland_mansion.ui.menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuItem extends Pane {
	private final Font font = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);
	private final Text mainMenuText;
	
	public MenuItem(String text) {
		this.mainMenuText = new Text(text);
		mainMenuText.setFont(this.font);
		mainMenuText.setFill(Color.WHITE);
		mainMenuText.setTextAlignment(TextAlignment.CENTER);

		getChildren().add(this.mainMenuText);
	}
}
