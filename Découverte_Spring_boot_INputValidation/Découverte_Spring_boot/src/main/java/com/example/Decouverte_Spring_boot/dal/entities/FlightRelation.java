package com.example.Decouverte_Spring_boot.dal.entities;

import com.example.Decouverte_Spring_boot.dal.entities.fiscals.PilotEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;

@Entity(name = "Flight")
@Data
public class FlightRelation {
    @EmbeddedId
    private FlightRelationId id;
    private Duration flyTime;

    @ManyToOne
    @MapsId("pilotId")
    private PilotEntity pilot;
    @ManyToOne
    @MapsId("planeTypeId")
    private PlaneTypeEntity planeType;


    @Embeddable
    @Data
    public static class FlightRelationId implements Serializable {
        private Long pilotId;
        private Long planeTypeId;
    }
}
