package com.text_adventure.woodland_mansion.ui;

import java.util.Arrays;
import java.util.List;

import com.text_adventure.woodland_mansion.helper.Delay;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class UserCursor {
	private Image cursorIconSelect;
	private Image cursorIconTap;
	private Image inputCursor;

	private final Image cursorWork1 = new Image(
			getClass().getResource("/icons/cursor_work1.png").toExternalForm());
	private final Image cursorWork2 = new Image(
			getClass().getResource("/icons/cursor_work2.png").toExternalForm());
	private final Image cursorWork3 = new Image(
			getClass().getResource("/icons/cursor_work3.png").toExternalForm());
	private final Image cursorWork4 = new Image(
			getClass().getResource("/icons/cursor_work4.png").toExternalForm());
	private final Image cursorWork5 = new Image(
			getClass().getResource("/icons/cursor_work5.png").toExternalForm());
	private final Image cursorWork6 = new Image(
			getClass().getResource("/icons/cursor_work6.png").toExternalForm());
	private final Image cursorWork7 = new Image(
			getClass().getResource("/icons/cursor_work7.png").toExternalForm());
	private final Image cursorWork8 = new Image(
			getClass().getResource("/icons/cursor_work8.png").toExternalForm());

	private final List<Image> cursorWork = Arrays.asList(cursorWork1, cursorWork2, cursorWork3, cursorWork4,
			cursorWork5,
			cursorWork6,
			cursorWork7, cursorWork8);

	Delay delay;

	public UserCursor() {
	}

	public void setCursor(Scene scene) {
		this.cursorIconSelect = new Image(
				getClass().getResource("/icons/cursor_select.png").toExternalForm());
		this.cursorIconTap = new Image(
				getClass().getResource("/icons/cursor_select_tap.png").toExternalForm());
		scene.setCursor(new ImageCursor(this.cursorIconSelect));
		scene.setOnMousePressed(e -> scene.setCursor(new ImageCursor(this.cursorIconTap)));
		scene.setOnMouseReleased(e -> scene.setCursor(new ImageCursor(this.cursorIconSelect)));
	}

	public void loadCursorIcons(int cursorID, Scene scene) {
		this.delay = new Delay();
		var icon = cursorWork.get(cursorID);
		scene.setCursor(new ImageCursor(icon));
		if (cursorID < cursorWork.size() - 1) {
			delay.setDelay(200, () -> {
				loadCursorIcons(cursorID + 1, scene);
			});
		}
	}

	public void setInputCursor(UserInputField input) {
		this.inputCursor = new Image(getClass().getResource("/icons/cursor_pen.png").toExternalForm());
		input.setCursor(new ImageCursor(this.inputCursor));
	}
}