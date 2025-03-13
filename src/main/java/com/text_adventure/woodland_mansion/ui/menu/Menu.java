package com.text_adventure.woodland_mansion.ui.menu;

import java.util.List;

import com.text_adventure.woodland_mansion.game.Game;
import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserCursor;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Menu extends StackPane {
	private final HBox wrapMenuBox = new HBox();
	private final HBox wrapCreditBox = new HBox();

	private final ObservableList<MenuItem> creditsProgrammerData = FXCollections.observableArrayList();
	private final ObservableList<MenuItem> creditsDesignerData = FXCollections.observableArrayList();

	public Menu(BorderPane root, Scene scene) {
		wrapMenuBox.setAlignment(Pos.CENTER);
		wrapCreditBox.setAlignment(Pos.CENTER);

		initMenu(wrapMenuBox, root, scene);
		initCredits(wrapCreditBox, root);

		root.setCenter(wrapMenuBox);
	}

	public final void initMenu(Pane parentBox, BorderPane root, Scene scene) {
		MenuTitle menuTitle = createMenuTitle("Woodland Mansion");

		MenuItem playButton = createMenuButton("Play", () -> handlePlayButton(root, scene));
		playButton.setMaxWidth(USE_PREF_SIZE);
		MenuItem creditButton = createMenuButton("Credits", () -> root.setCenter(wrapCreditBox));
		creditButton.setMaxWidth(USE_PREF_SIZE);
		MenuItem exitButton = createMenuButton("Exit", Platform::exit);
		exitButton.setMaxWidth(USE_PREF_SIZE);

		VBox menuItemBox = new VBox(40, playButton, creditButton, exitButton);
		menuItemBox.setAlignment(Pos.CENTER);

		VBox menuBox = new VBox(80, menuTitle, menuItemBox);
		menuBox.setAlignment(Pos.CENTER);

		if (parentBox instanceof VBox) {
			((VBox) parentBox).setAlignment(Pos.CENTER);
		} else if (parentBox instanceof HBox) {
			((HBox) parentBox).setAlignment(Pos.CENTER);
		}

		if (parentBox instanceof StackPane) {
			StackPane.setAlignment(menuBox, Pos.CENTER);
		}

		if (parentBox != null && !parentBox.getChildren().contains(menuBox)) {
			parentBox.getChildren().addAll(menuBox);
		}
	}

	private MenuItem createMenuButton(String text, Runnable action) {
		MenuItem button = new MenuItem(text);
		button.setOnMouseClicked(e -> action.run());
		return button;
	}

	private void handlePlayButton(BorderPane root, Scene scene) {
		UserCursor loadCursor = new UserCursor();
		loadCursor.loadCursorIcons(0, scene);

		Delay delay = new Delay();
		delay.setDelay(2000, () -> {
			root.getChildren().clear();
			Game game = new Game(root, scene);
			loadCursor.setCursor(scene);
			root.getChildren().addAll(game);
		});
	}

	public final void initCredits(Pane parentBox, BorderPane root) {
		MenuTitle creditProgrammerTitle = createMenuTitle("Programmer");
		VBox creditsProgrammerBox = createMenuItems(creditsProgrammerData, "Andrej", "Maxi");

		MenuTitle creditDesignerTitle = createMenuTitle("Designer");
		VBox creditsDesignerBox = createMenuItems(creditsDesignerData, "Saschinka");

		MenuItem backButton = new MenuItem("Back");
		backButton.setMaxWidth(USE_PREF_SIZE);
		backButton.setOnMouseClicked(e -> root.setCenter(wrapMenuBox));

		VBox creditsProgramBox = new VBox(80, creditProgrammerTitle, creditsProgrammerBox);
		creditsProgramBox.setAlignment(Pos.CENTER);

		VBox creditsDesignBox = new VBox(80, creditDesignerTitle, creditsDesignerBox);
		creditsDesignBox.setAlignment(Pos.CENTER);

		VBox wrapCreditsBox = new VBox(60, creditsProgramBox, creditsDesignBox, backButton);
		wrapCreditsBox.setAlignment(Pos.CENTER);

		if (!parentBox.getChildren().contains(wrapCreditsBox)) {
			parentBox.getChildren().add(wrapCreditsBox);
		}
	}

	private MenuTitle createMenuTitle(String title) {
		MenuTitle menuTitle = new MenuTitle(title);
		menuTitle.setPadding(new Insets(0, 0, 15, 0));
		return menuTitle;
	}

	private VBox createMenuItems(List<MenuItem> dataList, String... names) {
		VBox box = new VBox();
		box.setSpacing(20);
		box.setAlignment(Pos.CENTER);

		for (String name : names) {
			MenuItem item = new MenuItem(name);
			item.setMaxWidth(USE_PREF_SIZE);
			dataList.add(item);
			box.getChildren().add(item);
		}

		return box;
	}
}