package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserInputField;
import com.text_adventure.woodland_mansion.ui.UserOutputArea;
import com.text_adventure.woodland_mansion.ui.menu.Menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class Game extends Pane {
	private final Font exitButtonFont = Font.loadFont(getClass()
			.getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 34);

	private Image levelImage;
	private ImageView levelImageView;

	private CommandManager commandManager;
	private UserOutputArea gameUserOutput;
	private UserInputField gameUserInput;

	private final HBox wrapGameBox = new HBox();
	private final VBox gameBox = new VBox();

	private final Room[][][] mansion = new Room[4][3][3];
	private Player player;
	private Delay delay;

	public Game(BorderPane root, Scene scene) {
		wrapGameBox.setAlignment(Pos.CENTER);
		createGame(wrapGameBox, root, scene);
		root.setCenter(wrapGameBox);
	}

	public void initGame(UserOutputArea output, UserInputField input, BorderPane root, Scene scene, Room playerLocation,
			CommandManager commandManager) {
		this.player = new Player(100, 0, 0, "Fist");
		this.delay = new Delay();

		delay.setDelay(1000, () -> {
			output.appendTextOutput("You wake up in a dark, moist mansion...");
		});

		delay.setDelay(3000, () -> {
			output.appendTextOutput("'Where am I?' you ask yourself");
		});

		delay.setDelay(5000, () -> {
			output.appendTextOutput("'and... what is my name?'");
		});

		delay.setDelay(7000, () -> {
			output.appendTextOutput("I have to find a way out of this uncanny place!");
		});

		delay.setDelay(9000, () -> {
			output.appendTextOutput("Type 'help' to show a list of commands." + "\n");
		});

		initCommands(root, scene, input, output, playerLocation, commandManager);
		initWorld(mansion);
	}

	public void initCommands(BorderPane root, Scene scene, UserInputField input, UserOutputArea output,
			Room playerLocation,
			CommandManager commandManager) {
		commandManager.registerCommand("help",
				new Command("help", "- Print all commands -", () -> runHelp(input, output, commandManager)));
		commandManager.registerCommand("status",
				new Command("status", "- Check player status -", () -> runPlayerStats(output)));
		commandManager.registerCommand("look around", new Command("location", "- Look around your environment -",
				() -> runLookAround(output,
						mansion[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)])));
		commandManager.registerCommand("suicide",
				new Command("suicide", "- Kill yourself -", () -> runGameOver(root, scene)));
		commandManager.registerCommand("move",
				new Command("move", "- Start moving in a direction -", () -> runMove(output, input, commandManager)));
		commandManager.registerCommand("attack",
				new Command("attack", "- Attack monster -", () -> runAttack(output, root, scene)));
		commandManager.registerCommand("flee",
				new Command("flee", "- Flee the current room -", () -> runFlee(output, root, scene)));
		commandManager.registerCommand("heal",
				new Command("heal", "- Use heal potion -", () -> runUseHealItem(output)));
		commandManager.registerCommand("unlock", new Command("unlock", "- Key that unlocks the front door -",
				() -> runUseKeyItem(output, root, scene)));
	}

	public void initWorld(Room[][][] room) {
		// cellar
		room[0][0][0] = new Room(1, "up", "staircase", false, false, true, false);
		// no room 2
		room[2][0][0] = new Room(3, "", "", false, true, true, false);
		room[3][0][0] = new Room(4, "", "", false, false, false, true);
		room[0][1][0] = new Room(5, "", "", true, true, false, false);
		room[1][1][0] = new Room(6, "", "", false, true, false, true);
		room[2][1][0] = new Room(7, "", "", true, false, true, true);
		room[3][1][0] = new Room(8, "", "", false, false, true, false);
		// no room 9
		// no room 10
		room[2][2][0] = new Room(11, "", "", true, true, false, false);
		room[3][2][0] = new Room(12, "", "", true, false, false, true);

		// ground floor
		room[0][0][1] = new Room(13, "down", "staircase", false, false, true, false);
		// no room 14
		// no room 15
		// no room 16
		room[0][1][1] = new Room(17, "", "", true, true, true, false);
		room[1][1][1] = new Room(18, "", "", false, true, true, true);
		room[2][1][1] = new Room(19, "", "", false, true, true, true);
		room[3][1][1] = new Room(20, "up", "ladder", false, false, false, true);
		room[0][2][1] = new Room(21, "", "", true, true, false, false);
		room[1][2][1] = new Room(22, "", "", true, false, false, true);
		room[2][2][1] = new Room(23, "", "", true, false, false, false);
		// no room 24

		// attic
		// no room 25
		// no room 26
		// no room 27
		// no room 28
		room[0][1][2] = new Room(29, "", "", false, true, false, false);
		room[1][1][2] = new Room(30, "", "", false, true, false, true);
		room[2][1][2] = new Room(31, "", "", false, true, false, true);
		room[3][1][2] = new Room(32, "down", "ladder", false, false, false, true);
		// no room 33
		// no room 34
		// no room 35
		// no room 36

		// fill rooms with items
		room[3][0][0].setHealthItem(true); // room 4
		room[1][1][0].setHealthItem(true); // room 6
		room[3][1][0].setBeginnerWeapon(true); // room 8
		room[2][2][0].setMonster(true); // room 11
		room[0][1][1].setMonster(true); // room 17
		room[1][2][1].setMonster(true); // room 22
		room[1][2][1].setStrongWeapon(true); // room 22
		room[1][2][1].setHealthItem(true); // room 22
		room[2][2][1].setExitRoom(true); // room 23
		room[0][1][2].setExitKey(true); // room 29
		room[1][1][2].setMonster(true); // room 30
	}

	public void createGame(Pane parentBox, BorderPane root, Scene scene) {
		this.levelImage = new Image(getClass().getResource("/images/mansion.jpg").toExternalForm());
		this.levelImageView = new ImageView(levelImage);
		levelImageView.setFitHeight(550);
		levelImageView.setFitWidth(1200);

		this.gameUserOutput = new UserOutputArea();
		this.commandManager = new CommandManager();
		this.gameUserInput = new UserInputField(this.gameUserOutput, this.commandManager);

		Text exitButton = new Text("Exit to Menu");
		exitButton.setFill(Color.WHITE);
		exitButton.setFont(this.exitButtonFont);
		exitButton.setTranslateY(25);
		exitButton.setOnMouseClicked(e -> {
			getChildren().clear();
			Menu menu = new Menu(root, scene);
			root.getChildren().addAll(menu);
		});

		gameBox.setAlignment(Pos.CENTER_LEFT);
		gameBox.getChildren().addAll(this.levelImageView, this.gameUserOutput, this.gameUserInput, exitButton);

		initGame(this.gameUserOutput, this.gameUserInput, root, scene, null, this.commandManager);

		parentBox.getChildren().add(this.gameBox);
	}

	public void runHelp(UserInputField input, UserOutputArea output, CommandManager commandManager) {
		commandManager.getCommands().forEach((name, cmd) -> {
			output.appendTextOutput(name + "\t" + cmd.getDescription());
		});
	}

	public void runPlayerStats(UserOutputArea output) {
		player.printPlayerHealth(output);
		player.printPlayerInventory(output);
	}

	public void runLookAround(UserOutputArea output, Room room) {
		player.lookAround(room, player, output);
	}

	public void runMove(UserOutputArea output, UserInputField input, CommandManager commandManager) {
		player.move(output, input, commandManager,
				mansion[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)]);
	}

	public void runAttack(UserOutputArea output, BorderPane root, Scene scene) {
		player.attack(mansion[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)], output);

		if (player.getHealth() <= 0) {
			runGameOver(root, scene);
		}
	}

	public void runFlee(UserOutputArea output, BorderPane root, Scene scene) {
		player.flee(mansion[player.getPositionXYZ(0)][player.getPositionXYZ(1)][player.getPositionXYZ(2)], output);

		if (player.getHealth() <= 0) {
			runGameOver(root, scene);
		}
	}

	public void runUseHealItem(UserOutputArea output) {
		player.useHealthItem(output);
	}

	public void runUseKeyItem(UserOutputArea output, BorderPane root, Scene scene) {
		player.useExitKey(output, root, scene);
	}

	public void runGameOver(BorderPane root, Scene scene) {
		root.getChildren().clear();
		GameOver gameOver = new GameOver(root, scene);
		root.getChildren().add(gameOver);
	}
}
