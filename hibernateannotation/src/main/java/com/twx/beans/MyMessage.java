package com.twx.beans;

import javax.persistence.*;

/**
 * Created by twx on 2017/6/29.
 */
@Entity
@Table(name = "user")
public class MyMessage {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
