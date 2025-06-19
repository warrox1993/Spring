package com.example.Decouverte_Spring_boot.dal.entities;

import com.example.Decouverte_Spring_boot.dal.entities.fiscals.MechanicEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Intervention")
@Data
public class InterventionEntity extends AbstractEntity {
    private String number;
    private String object;
    private Duration duration;
    private LocalDate date;

    @Transient
    private LocalDateTime startAt;
    @Transient
    private LocalDateTime endAt;

    private Long planeId;
    private Long reparerId;
    private Long checkerId;

    @ManyToOne()
    @JoinColumn(name = "planeId", insertable = false, updatable = false)
    private PlaneEntity plane;
    @ManyToOne
    @JoinColumn(name = "checkerId", insertable = false, updatable = false)
    private MechanicEntity checker;
    @ManyToOne
    @JoinColumn(name = "reparerId", insertable = false, updatable = false)
    private MechanicEntity reparer;

    @PrePersist
    public void setStartAt() {
        this.calculDuration();
    }

    @PreUpdate
    public void calculDuration() {
        LocalDateTime startAt = this.startAt;
        LocalDateTime endAt = this.endAt;
        if (startAt != null && endAt != null) {
            this.duration = Duration.between(startAt, endAt);
        } else {
            this.duration = null;
        }
    }
}
