package model;

import java.io.Serializable;

public class Note implements Serializable {

    private String title;
    private String content;
    private static int idCount = 0;

    public Note() {};

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
