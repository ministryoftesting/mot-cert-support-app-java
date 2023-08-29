package com.ministryoftesting.models;

public class CreatedID {

    private int id;

    public CreatedID(int id) {
        this.id = id;
    }

    public CreatedID() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreatedID{" +
                "id=" + id +
                '}';
    }
}
