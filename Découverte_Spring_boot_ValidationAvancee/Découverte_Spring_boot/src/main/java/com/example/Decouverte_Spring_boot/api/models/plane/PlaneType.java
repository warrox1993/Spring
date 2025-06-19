package com.example.Decouverte_Spring_boot.api.models.plane;

public enum PlaneType {
    CARGO(2),
    PASSAGER(50);

    public final int minSeats;

    PlaneType(int minSeats) {
        this.minSeats = minSeats;
    }
}
