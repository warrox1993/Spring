package com.example.Decouverte_Spring_boot.dal.entities;


import com.example.Decouverte_Spring_boot.dal.entities.fiscals.MechanicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity(name = "PlaneType")
@Data
public class PlaneTypeEntity extends AbstractEntity {
    private String name;
    private String constructorName;
    private int enginePower;
    private int nbPlaces;

    @ManyToMany
    private List<MechanicEntity> mechanics;

    @OneToMany(mappedBy = "planeType")
    private List<FlightRelation> flights;
}
