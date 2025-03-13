package com.text_adventure.woodland_mansion.ui.menu;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuTitle extends StackPane {
	private static final Font DEFAULT_FONT = Font.font("Arial", 48);
	private final Text mainTitle;

	public MenuTitle(String name) {
		Font font;
		try {
			font = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);
			if (font == null)
				font = DEFAULT_FONT;
		} catch (Exception ex) {
			font = DEFAULT_FONT;
		}

		StringBuilder spread = new StringBuilder();
		for (char c : name.toCharArray()) {
			spread.append(c).append(" ");
		}

		this.mainTitle = new Text(spread.toString().trim());
		mainTitle.setFont(font);
		mainTitle.setFill(Color.WHITE);
		mainTitle.setTextAlignment(TextAlignment.CENTER);

		getChildren().add(this.mainTitle);
	}
}
