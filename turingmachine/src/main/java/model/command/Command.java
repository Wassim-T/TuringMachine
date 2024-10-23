package model.command;

/**
 * Command interface with undo method
 */
public interface Command {
    /**
     * Executes the specified command
     */
    public void execute();

    /**
     * undoes the specified command
     */
    public void undo();
}
