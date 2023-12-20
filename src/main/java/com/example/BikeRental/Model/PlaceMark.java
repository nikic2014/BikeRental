package com.example.BikeRental.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "PlaceMark")
public class PlaceMark {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "address")
    private String address;

    @Column(name = "tag")
    private String tag;

    @Column(name = "description")
    private String description;

    public PlaceMark() {
    }

    public PlaceMark(String title, String address, String tag, String description) {
        this.title = title;
        this.address=address;
        this.tag=tag;
        this.description=description;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.title + " " + this.address + " "
                + this.tag + " " + this.description;
    }
}
