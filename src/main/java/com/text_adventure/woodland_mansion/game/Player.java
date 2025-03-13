package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.ui.UserInputField;
import com.text_adventure.woodland_mansion.ui.UserOutputArea;
import com.text_adventure.woodland_mansion.ui.menu.Menu;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Player {
	private final int[] positionXYZ = { 3, 2, 0 };
	private int health = 100;
	private int healthItemsInInventory = 0;
	private int exitKeyInInventory = 0;
	private String equippedWeapon = "Fist";
	private boolean hasExitKey;

	Player(int health, int healthItemsInInventory, int exitKeyInInventory, String equippedWeapon) {
		this.health = health;
		this.healthItemsInInventory = healthItemsInInventory;
		this.exitKeyInInventory = exitKeyInInventory;
		this.equippedWeapon = equippedWeapon;
	}

	public void lookAround(Room playerLocation, Player player, UserOutputArea output) {
		output.appendTextOutput(
				"\n" + "Room: " + playerLocation.getRoomID() + "\n" + "Floor: "
						+ playerLocation.getCurrentFloor(player));

		boolean[] doorCheckArray = { playerLocation.getDoorNorth(), playerLocation.getDoorEast(),
				playerLocation.getDoorSouth(), playerLocation.getDoorWest() };
		int firstdirection = -1;
		StringBuilder doorText = new StringBuilder("You see doors that lead ");

		for (int i = 0; i < doorCheckArray.length; i++) {
			if (doorCheckArray[i]) {
				if (firstdirection != -1) {
					doorText.append(", ");
				}

				switch (i) {
					case 0:
						doorText.append("north");
						break;
					case 1:
						doorText.append("east");
						break;
					case 2:
						doorText.append("south");
						break;
					case 3:
						doorText.append("west");
						break;
				}

				firstdirection = i;
			}
		}

		doorText.append(".");
		output.appendTextOutput(doorText.toString());

		if ("up".equals(playerLocation.getVerticalPath()) || "down".equals(playerLocation.getVerticalPath())) {
			output.appendTextOutput("You also see a  " + playerLocation.getVerticalPathType() + " that leads "
					+ playerLocation.getVerticalPath() + ".");
		}

		if (playerLocation.getHealthItem() == true) {
			player.pickupHealthItem(playerLocation, output);
		}

		if (playerLocation.getBeginnerWeapon() == true) {
			player.pickupBeginnerWeapon(playerLocation, output);
		}

		if (playerLocation.getStrongWeapon() == true) {
			player.pickupStrongWeapon(playerLocation, output);
		}

		if (playerLocation.getExitKey() == true) {
			player.pickupExitKey(playerLocation, output);
		}

		if (playerLocation.getExitRoom() == true) {
			output.appendTextOutput("\n" + "You found the front door. It's locked.");
			output.appendTextOutput("...");
			if (this.exitKeyInInventory > 0) {
				output.appendTextOutput("You have the key to unlock the front door.");
			} else {
				output.appendTextOutput("You need a key to unlock the front door.");
			}
		}

		if (playerLocation.getMonster() == true) {
			output.appendTextOutput("\n" + "You see a monster in the room. It looks dangerous.");
			if (player.getEquippedWeapon().equals("Fist")) {
				output.appendTextOutput("You don't have a weapon to defend yourself. Run!");
			} else {
				output.appendTextOutput("You have a " + player.getEquippedWeapon() + " equipped.");
			}
		}
	}

	public void move(UserOutputArea output, UserInputField input, CommandManager commandManager,
			Room playerLocation) {
		if (playerLocation.getMonster() == true) {
			output.appendTextOutput("You can't move while the monster is in the room." + "\n");
			return;
		}

		output.appendTextOutput("Where do you want to move? North, East, South, West, Up, Down?");

		commandManager.waitForMoveDirection(direction -> {
			switch (direction.toLowerCase()) {
				case "north":
					if (getPositionXYZ(1) > 0 && playerLocation.getDoorNorth()) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) - 1, getPositionXYZ(2));
						output.appendTextOutput("You moved North." + "\n");
					} else {
						output.appendTextOutput("There's no door you can go through!!!" + "\n");
					}
					break;

				case "east":
					if (getPositionXYZ(0) < 3 && playerLocation.getDoorEast()) {
						setPositionXYZ(getPositionXYZ(0) + 1, getPositionXYZ(1), getPositionXYZ(2));
						output.appendTextOutput("You moved East." + "\n");
					} else {
						output.appendTextOutput("There's no door you can go through!!!" + "\n");
					}
					break;

				case "south":
					if (getPositionXYZ(1) < 2 && playerLocation.getDoorSouth()) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) + 1, getPositionXYZ(2));
						output.appendTextOutput("You moved South." + "\n");
					} else {
						output.appendTextOutput("There's no door you can go through!!!" + "\n");
					}
					break;

				case "west":
					if (getPositionXYZ(0) > 0 && playerLocation.getDoorWest()) {
						setPositionXYZ(getPositionXYZ(0) - 1, getPositionXYZ(1), getPositionXYZ(2));
						output.appendTextOutput("You moved West." + "\n");
					} else {
						output.appendTextOutput("There's no door you can go through!!!" + "\n");
					}
					break;

				case "up":
					if ("up".equals(playerLocation.getVerticalPath()) &&
							("staircase".equals(playerLocation.getVerticalPathType())
									|| "ladder".equals(playerLocation.getVerticalPathType()))) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) + 1);
						output.appendTextOutput("You moved up." + "\n");
					} else {
						output.appendTextOutput("There's no way up. Guess I'll try something else..." + "\n");
					}
					break;

				case "down":
					if ("down".equals(playerLocation.getVerticalPath()) &&
							"ladder".equals(playerLocation.getVerticalPathType())) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) - 1);
						output.appendTextOutput("You moved down." + "\n");
					} else {
						output.appendTextOutput("There's no way down. Guess I'll try something else..." + "\n");
					}
					break;

				default:
					output.appendTextOutput("Invalid direction! Try North, East, South, West, Up, or Down." + "\n");
					break;
			}
		});
	}

	public void attack(Room playerLocation, UserOutputArea output) {
		if (playerLocation.getMonster() == true && !"Fist".equals(this.equippedWeapon)) {
			output.appendTextOutput("You attacked the monster with your " + this.equippedWeapon + ".");
			output.appendTextOutput("The monster is dead.");
			playerLocation.setMonster(false);
		} else if (playerLocation.getMonster() == true && "Fist".equals(this.equippedWeapon)) {
			output.appendTextOutput("You don't have a weapon to attack the monster.");
			output.appendTextOutput("The monster damaged you.");
			this.health = this.health - 20;
		} else {
			output.appendTextOutput("There's no monster in the room to attack.");
		}
	}

	public void flee(Room playerLocation, UserOutputArea output) {
		if (playerLocation.getMonster() == true) {
			output.appendTextOutput("You ran away from the monster. But you took some damage.");
			this.health = this.health - 10;
			playerLocation.setMonster(false);
		} else {
			output.appendTextOutput("There's no monster in the room to flee from.");
		}
	}

	public void printPlayerHealth(UserOutputArea output) {
		output.appendTextOutput("\n" + "Lifepoints: " + getHealth());
	}

	public void printPlayerInventory(UserOutputArea output) {
		output.appendTextOutput("Inventory:");

		if (this.healthItemsInInventory > 0 || this.exitKeyInInventory > 0 || !"Fist".equals(this.equippedWeapon)) {
			output.appendTextOutput("First Aid Kit: " + this.healthItemsInInventory + "x");
			output.appendTextOutput("Exit Key: " + this.exitKeyInInventory + "x");
			output.appendTextOutput("Equipped Weapon: " + this.equippedWeapon);
		} else {
			output.appendTextOutput("empty");
		}
	}

	public void pickupHealthItem(Room playerlocation, UserOutputArea output) {
		if (playerlocation.getHealthItem()) {
			output.appendTextOutput("\n" + "While searching the room you found a first aid kit." + "\n" +
					"You store the kit in your Inventory.");
			this.healthItemsInInventory += 1;
			playerlocation.setHealthItem(false);
		}
	}

	public void pickupExitKey(Room playerlocation, UserOutputArea output) {
		if (playerlocation.getExitKey()) {
			output.appendTextOutput("\n" + "You found a key that looks like it could open the front door." + "\n" +
					"You store the key in your Inventory.");
			this.exitKeyInInventory += 1;
			playerlocation.setExitKey(false);
		}
	}

	public void pickupBeginnerWeapon(Room playerlocation, UserOutputArea output) {
		if (playerlocation.getBeginnerWeapon()) {
			output.appendTextOutput(
					"In the corner of the room you see a broomstick." + "\n"
							+ "One end is broken and sharp, you could use it to defend yourself!");
			equippedWeapon = "Broomstick";
			playerlocation.setBeginnerWeapon(false);
		}
	}

	public void pickupStrongWeapon(Room playerlocation, UserOutputArea output) {
		if (playerlocation.getStrongWeapon()) {
			output.appendTextOutput(
					"You look in a cupboard and find an aluminum baseball bat." + "\n"
							+ "This thing will do heaps of damage!");
			equippedWeapon = "Baseball Bat";
			playerlocation.setStrongWeapon(false);
		}
	}

	public void useHealthItem(UserOutputArea output) {
		if (healthItemsInInventory > 0) {
			if (this.health + 50 >= 100) {
				this.health = 100;
			} else {
				this.health = this.health + 50;
			}

			output.appendTextOutput("You feel better now." + "\n" + "Health: " + this.health);
			this.healthItemsInInventory -= 1;
		} else {
			output.appendTextOutput("You don't have any health items in your inventory.");
		}
	}

	public void useExitKey(UserOutputArea output, BorderPane root, Scene scene) {
		if (exitKeyInInventory > 0) {
			this.exitKeyInInventory -= 1;
			output.appendTextOutput("You use the key to unlock the front door." + "\n" + "You escaped the mansion!");
			Menu menu = new Menu(root, scene);
			root.getChildren().addAll(menu);
		} else {
			output.appendTextOutput("You don't have the key to unlock the front door.");
		}
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPositionXYZ(int i) {
		return positionXYZ[i];
	}

	public void setPositionXYZ(int x, int y, int z) {
		this.positionXYZ[0] = x;
		this.positionXYZ[1] = y;
		this.positionXYZ[2] = z;
	}

	public int getHealthItemsInInventory() {
		return this.healthItemsInInventory;
	}

	public void setHealthItemsInInventory(int healthItemsInInventory) {
		this.healthItemsInInventory = healthItemsInInventory;
	}

	public String getEquippedWeapon() {
		return this.equippedWeapon;
	}

	public void setEquippedWeapon(String equippedWeapon) {
		this.equippedWeapon = equippedWeapon;
	}

	public boolean isHasExitKey() {
		return hasExitKey;
	}

	public void setHasExitKey(boolean hasExitKey) {
		this.hasExitKey = hasExitKey;
	}
}
