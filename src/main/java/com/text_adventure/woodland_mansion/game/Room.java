package com.text_adventure.woodland_mansion.game;

import javafx.scene.layout.Pane;

public class Room extends Pane {
	private int roomID;

	private String verticalPath;
	private String verticalPathType;

	private boolean doorNorth = false;
	private boolean doorEast = false;
	private boolean doorSouth = false;
	private boolean doorWest = false;
	private boolean healthItem = false;
	private boolean beginnerWeapon = false;
	private boolean strongWeapon = false;
	private boolean exitRoom = false;
	private boolean exitKey = false;
	private boolean monster = false;

	public Room() {
		this.roomID = 1;
	}

	public Room(int id, String verticalPath, String verticalPathType, boolean doorNorth, boolean doorEast,
			boolean doorSouth, boolean doorWest) {

		this.roomID = id;
		this.verticalPath = verticalPath;
		this.verticalPathType = verticalPathType;
		this.doorNorth = doorNorth;
		this.doorEast = doorEast;
		this.doorSouth = doorSouth;
		this.doorWest = doorWest;

	}

	public String getCurrentFloor(Player player) {

		switch (player.getPositionXYZ(2)) {
			case 2:
				return "Attic";
			case 1:
				return "Ground Floor";
			default:
				return "Cellar";
		}

	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int id) {
		this.roomID = id;
	}

	public String getVerticalPath() {
		return verticalPath;
	}

	public void setVerticalPath(String vertical_Path) {
		verticalPath = vertical_Path;
	}

	public String getVerticalPathType() {
		return verticalPathType;
	}

	public void setVerticalPathType(String verticalpath_type) {
		this.verticalPathType = verticalpath_type;
	}

	public boolean getDoorNorth() {
		return doorNorth;
	}

	public void setDoorNorth(boolean doorNorth) {
		this.doorNorth = doorNorth;
	}

	public boolean getDoorEast() {
		return doorEast;
	}

	public void setDoorEast(boolean doorEast) {
		this.doorEast = doorEast;
	}

	public boolean getDoorSouth() {
		return doorSouth;
	}

	public void setDoorSouth(boolean doorSouth) {
		this.doorSouth = doorSouth;
	}

	public boolean getDoorWest() {
		return doorWest;
	}

	public void setDoorWest(boolean doorWest) {
		this.doorWest = doorWest;
	}

	public boolean getHealthItem() {
		return healthItem;
	}

	public void setHealthItem(boolean healthItem) {
		this.healthItem = healthItem;
	}

	public boolean getBeginnerWeapon() {
		return beginnerWeapon;
	}

	public void setBeginnerWeapon(boolean beginnerWeapon) {
		this.beginnerWeapon = beginnerWeapon;
	}

	public boolean getStrongWeapon() {
		return strongWeapon;
	}

	public void setStrongWeapon(boolean strongWeapon) {
		this.strongWeapon = strongWeapon;
	}

	public boolean getExitRoom() {
		return exitRoom;
	}

	public void setExitRoom(boolean exitRoom) {
		this.exitRoom = exitRoom;
	}

	public boolean getExitKey() {
		return exitKey;
	}

	public void setExitKey(boolean exitKey) {
		this.exitKey = exitKey;
	}

	public boolean getMonster() {
		return monster;
	}

	public void setMonster(boolean monster) {
		this.monster = monster;
	}
}
