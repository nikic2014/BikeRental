package com.example.BikeRental.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Image")
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "src")
    private String src;
    @Column(name = "idPlaceMark")
    private int idPlaceMark;

    public Image() {

    }

    public Image(String src, int idPlaceMark){
        this.src=src;
        this.idPlaceMark=idPlaceMark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getIdPlaceMark() {
        return idPlaceMark;
    }

    public void setIdPlaceMark(int idPlaceMark) {
        this.idPlaceMark = idPlaceMark;
    }

    public String toString(){
        return this.id + " " + this.src + " " + this.idPlaceMark;
    }
}
