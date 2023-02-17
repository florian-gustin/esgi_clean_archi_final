package org.example.domain;

import java.util.Date;
import java.util.List;

public class Task {

    private int id;
    private Date created_at;
    private Date update_at;
    private Date end_at;
    private String description;
    private int state;
    private String tag;
    private List<Task> subTask;

    public Date getUpdate_at() {
        return update_at;
    }

    public int getId() {
        return id;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }

    public String getTag() {
        return tag;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public List<Task> getSubTask() {
        return subTask;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setSubTask(List<Task> subTask) {
        this.subTask = subTask;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
