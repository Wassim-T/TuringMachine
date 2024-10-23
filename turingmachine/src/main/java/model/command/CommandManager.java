package model.command;

import java.util.Stack;

/**
 * Manages the execution, undo, and redo of commands in the AsciiPaint application.
 * Keeps track of the commands using undo and redo stacks.
 */
public class CommandManager {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    /**
     * Constructs a CommandManager with empty undo and redo stacks.
     */
    public CommandManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Executes the specified command, clears the redo stack, and adds the command to the undo stack.
     *
     * @param command The command to be executed.
     */
    public void executeCommand(Command command) {
        redoStack.clear();
        command.execute();
        undoStack.push(command);

    }



    /**
     * Undoes the last executed command, moves it from the undo stack to the redo stack.
     * Prints a message if there are no commands to undo.
     */
    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            Command toUndo = undoStack.pop();
            toUndo.undo();
            redoStack.push(toUndo);
        } else {
            System.out.println("No commands to undo!");
        }
    }

    /**
     * Redoes the last undone command, moves it from the redo stack to the undo stack.
     * Prints a message if there are no commands to redo.
     */
    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            Command toRedo = redoStack.pop();
            toRedo.execute();
            undoStack.push(toRedo);
        } else {
            System.out.println("No commands to redo!");
        }
    }
}
