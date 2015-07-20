package org.webscada.controllers.tree_edit_delegation;

public class AddTcpOperation implements Command {
    private Operation operation;

    public AddTcpOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String execute() {
        return operation.addTcp();
    }
}
