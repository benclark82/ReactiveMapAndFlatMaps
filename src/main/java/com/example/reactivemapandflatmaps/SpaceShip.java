package com.example.reactivemapandflatmaps;

import lombok.Data;

@Data
public class SpaceShip {

    private int id;
    private String name;
    private int crew;

    public SpaceShip(String name, int crew) {
        this.name = name;
        this.crew = crew;
    }

    public SpaceShip(int id, String name, int crew) {
        this.id = id;
        this.name = name;
        this.crew = crew;
    }
}
