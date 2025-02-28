package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.ui.UserInputField;
import com.text_adventure.woodland_mansion.ui.UserOutputArea;

public class Player {
	@SuppressWarnings("FieldMayBeFinal")
	private int[] positionXYZ = { 0, 0, 0 };

	private String name;
	private int health = 100;
	private int healthItemsInInventory = 0;
	private String equippedWeapon = "None";

	Player(String name) {
		this.setName(name);
	}

	public void printPlayerName(UserOutputArea output) {
		output.printTextLine("Name: " + getName());
	}

	public void printPlayerHealth(UserOutputArea output) {
		output.printTextLine("Lifepoints: " + getHealth());
	}

	public void printPlayerInventory(UserOutputArea output) {
		output.printTextLine("Inventory:");

		if (this.healthItemsInInventory > 0) {
			output.printTextLine("First Aid Kit: " + this.healthItemsInInventory + "x");
		} else {
			output.printTextLine("empty\n");
		}
	}

	public void move(UserOutputArea output, UserInputField input, Room playerlocation) {
		boolean loop = true;

		String move = "";

		while (loop == true) {
			switch (move) {
				case "North":
					if (getPositionXYZ(1) > 0 && playerlocation.isHasDoorNorth() == true) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) - 1, getPositionXYZ(2));
						loop = false;
					} else {
						output.printTextLine("Theres no door you're able to go through!!!");
					}
					break;

				case "East":
					if (getPositionXYZ(0) < 3 && playerlocation.isHasDoorEast() == true) {
						setPositionXYZ(getPositionXYZ(0) + 1, getPositionXYZ(1), getPositionXYZ(2));
						loop = false;
					} else {
						output.printTextLine("Theres no door you're able to go through!!!");
					}
					break;

				case "South":
					if (getPositionXYZ(1) < 2 && playerlocation.isHasDoorEast() == true) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1) + 1, getPositionXYZ(2));
						loop = false;
					} else {
						output.printTextLine("Theres no door you're able to go through!!!");
					}
					break;

				case "West":
					if (getPositionXYZ(0) > 0 && playerlocation.isHasDoorWest() == true) {
						setPositionXYZ(getPositionXYZ(0) - 1, getPositionXYZ(1), getPositionXYZ(2));
						loop = false;
					} else {
						output.printTextLine("Theres no door you're able to go through!!!");
					}
					break;

				case "Up":
					if ("up".equals(playerlocation.getVerticalPath())
							&& "staircase".equals(playerlocation.getVerticalPathType())
							|| "up".equals(playerlocation.getVerticalPath())
									&& "ladder".equals(playerlocation.getVerticalPathType())) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) + 1);
						loop = false;
					} else {
						output.printTextLine("Theres no way up. Guess I'll try something else...");
					}
					break;

				case "Down":
					if ("down".equals(playerlocation.getVerticalPath())
							&& "ladder".equals(playerlocation.getVerticalPathType())
							|| "down".equals(playerlocation.getVerticalPath())
									&& "ladder".equals(playerlocation.getVerticalPathType())) {
						setPositionXYZ(getPositionXYZ(0), getPositionXYZ(1), getPositionXYZ(2) - 1);
					} else {
						output.printTextLine("Theres no way down. Guess I'll try something else...");
					}
					break;

				case "Cancel":
					loop = false;
					break;

				default:
					output.printTextLine("Command not found");
					break;
			}
		}
	}

	public void attack() {
	}

	public void pickupHealthItem(Room playerlocation) {
		if (playerlocation.isHasHealthItem() == true) {
			this.healthItemsInInventory += 1;
			playerlocation.setHasHealthItem(false);
		}
	}

	public void useHealthItem(UserOutputArea output) {
		if (this.health + 50 >= 100) {
			this.health = 100;
		} else {
			this.health = this.health + 50;
		}

		output.printTextLine("You feel better now.\nhealth: " + this.health);
		this.healthItemsInInventory -= 1;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return this.name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public int getPositionXYZ(int i) {
		return this.positionXYZ[i];
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

}
