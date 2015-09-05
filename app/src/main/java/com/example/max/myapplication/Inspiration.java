package com.example.max.myapplication;

import java.io.Serializable;

/**
 * Created by max on 02/09/15.
 */
public class Inspiration implements Serializable {
    private String title;

    private int created, changed;

    public Inspiration(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }
}
