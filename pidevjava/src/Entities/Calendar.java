/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Administrateur
 */
public class Calendar {
    private int id;
    private String title ;
    private Date start;
    private Date end;
    private String description;
    private String background_color;
    private String text_color;
    private String border_color;

    public Calendar(int id, String title, Date end, String description, String background_color, String text_color, String border_color) {
        this.id = id;
        this.title = title;
        this.end = end;
        this.description = description;
        this.background_color = background_color;
        this.text_color = text_color;
        this.border_color = border_color;
    }

    public Calendar(int id, String title, Date start, Date end, String description, String background_color, String text_color, String border_color) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.background_color = background_color;
        this.text_color = text_color;
        this.border_color = border_color;
    }

    public Calendar() {
    }

    public Calendar(String title, String description , Date start, Date end) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
    }
    
     public void ajouter(String summary, String description, Date sqlDate1, Date sqlDate2) {
        this.title = summary;
        this.start = sqlDate1;
        this.end = sqlDate2;
        this.description = description;
    }

    

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getBorder_color() {
        return border_color;
    }

    public void setBorder_color(String border_color) {
        this.border_color = border_color;
    }

    @Override
    public String toString() {
        return "calendar{" + "id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", description=" + description + ", background_color=" + background_color + ", text_color=" + text_color + ", border_color=" + border_color + '}';
    }

   

    
    
    
}
