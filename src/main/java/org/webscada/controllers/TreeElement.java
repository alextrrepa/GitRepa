package org.webscada.controllers;

public class TreeElement {
    private String id;
    private String parent;
    private String text;

    public TreeElement(String id, String parent, String text) {
        this.id = id;
        this.parent = parent;
        this.text = text;
    }
}
