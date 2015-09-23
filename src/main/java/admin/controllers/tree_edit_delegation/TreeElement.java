package admin.controllers.tree_edit_delegation;

import com.google.gson.annotations.Expose;

public class TreeElement {
    @Expose
    private String id;
    @Expose
    private String parent;
    @Expose
    private String text;
    @Expose
    private String icon;
    @Expose
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
