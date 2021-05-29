/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Task {
    private int id,status;
    private String name;
    
    public Task(int id, int status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public Task(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", status=" + status + ", name=" + name + '}';
    }
    
    
}
