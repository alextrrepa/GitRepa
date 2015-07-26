package org.webscada.controllers;

public class TreeElement {
    private String id;
    private String parent;
    private String text;
    private String icon;
    private String data;

    public TreeElement(String id, String parent, String text, String icon) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
    }

    public TreeElement(String id, String parent, String text, String icon, String data) {
        this.id = id;
        this.parent = parent;
        this.text = text;
        this.icon = icon;
        this.data = data;
    }
}
