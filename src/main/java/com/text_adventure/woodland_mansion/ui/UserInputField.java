package com.text_adventure.woodland_mansion.ui;

import java.util.function.Consumer;

import com.text_adventure.woodland_mansion.game.CommandManager;

import javafx.scene.ImageCursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class UserInputField extends javafx.scene.layout.Pane {
	private TextField input;
	private String inputText;

	private final Font gameInputFieldFont = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 28);

	private final Image inputCursor = new Image(getClass().getResource("/icons/cursor_pen.png").toExternalForm());
	private Consumer<String> inputListener;

	public UserInputField(UserOutputArea output, CommandManager commandManager) {
		this.input = new TextField();
		input.setPrefHeight(50);
		input.setPrefWidth(1200);
		input.setFont(this.gameInputFieldFont);
		input.clear();
		input.setStyle(
				"-fx-control-inner-background: #000000;" + "-fx-display-focus: none;" + "-fx-border-color: #ffffff;");

		input.setOnAction(e -> {
			this.inputText = input.getText().trim().toLowerCase();
			input.clear();

			if (commandManager.executeCommand(this.inputText)) {
				return;
			}

			output.appendTextOutput("Command not found");
			output.appendTextOutput("Type 'help' to show a list of commands.");

			if (inputListener != null) {
				inputListener.accept(this.inputText); // Callback aufrufen
			}
		});

		input.setCursor(new ImageCursor(this.inputCursor));
		getChildren().add(this.input);
	}

	public void setOnUserInput(Consumer<String> listener) {
		this.inputListener = listener;
	}
}
