package org.webscada.controllers.tree_edit_delegation;

public class DoOperation {
    private Command command;

    public DoOperation(Command command) {
        this.command = command;
    }

    public void makeCommand() {
        command.execute();
    }
}
