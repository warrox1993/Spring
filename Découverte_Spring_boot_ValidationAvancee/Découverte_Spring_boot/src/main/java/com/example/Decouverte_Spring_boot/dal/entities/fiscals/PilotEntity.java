package com.example.Decouverte_Spring_boot.dal.entities.fiscals;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "Pilot")
@Data
public class PilotEntity extends FiscalEntity {
    private String brevetNumber;
}
