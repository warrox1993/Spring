package com.example.Decouverte_Spring_boot.dal.entities;

import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "Plane")
@Data
public class PlaneEntity extends AbstractEntity {
    private String imma;

    private Long ownerId;
    private Long typeId;

    @ManyToOne
    @JoinColumn(name = "ownerId", insertable = false, updatable = false)
    private FiscalEntity owner;
    @ManyToOne
    @JoinColumn(name = "typeId", insertable = false, updatable = false)
    private PlaneTypeEntity type;

}
