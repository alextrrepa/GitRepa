package org.webscada.controllers.TreeEditDelegation;

public class TreeEditContext {
    private TreeEditStrategy strategy;

    public TreeEditContext(TreeEditStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.doAction();
    }
}
