package org.webscada.controllers.tree_edit_delegation;

public class AddOperation implements Command {
    private Operation operation;

    public AddOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.add();
    }
}
