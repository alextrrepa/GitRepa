package org.webscada.controllers.tree_edit_delegation;

public class DeleteOperation implements Command {
    private Operation operation;

    public DeleteOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.delete();
    }
}
