package com.text_adventure.woodland_mansion.game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private boolean waitingForMoveDirection = false;
    private Consumer<String> moveCallback;

    public Map<String, Command> getCommands() {
        return this.commands;
    }

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public boolean executeCommand(String name) {
        if (waitingForMoveDirection) {
            if (moveCallback != null) {
                moveCallback.accept(name);
            }
            waitingForMoveDirection = false;
            return true;
        }

        if (!commands.containsKey(name)) {
            return false;
        }
        commands.get(name).execute();
        return true;
    }

    public void waitForMoveDirection(Consumer<String> callback) {
        this.waitingForMoveDirection = true;
        this.moveCallback = callback;
    }
}
