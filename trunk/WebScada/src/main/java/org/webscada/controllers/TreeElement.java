package org.webscada.controllers;

public class TreeElement {
    private String id;
    private String parent;
    private String text;
    private String rel;

    public TreeElement(String id, String parent, String text, String rel) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.rel = rel;
    }
}
