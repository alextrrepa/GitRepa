package org.webscada.controllers.tree_edit_delegation;

public class AddRtuOperation implements Command {
    private Operation operation;

    public AddRtuOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute() {
//        return operation.addRtu();
        return null;
    }
}
