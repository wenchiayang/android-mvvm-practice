package com.example.architectureexample.model.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Room @Entity will create SQLite table and columns for us
@Entity(tableName = "note_table")
public class Note implements Serializable {
    // Room @PrimaryKey will generate id value automatically
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;

    // Room @ColumnInfo will change the column name to priority_column
    // By default, the column name is the variable name(priority)
    // @ColumnInfo(name = "priority_column")
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
