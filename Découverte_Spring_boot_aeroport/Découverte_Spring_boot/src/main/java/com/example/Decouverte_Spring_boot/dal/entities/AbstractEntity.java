package com.example.Decouverte_Spring_boot.dal.entities;

import com.example.Decouverte_Spring_boot.dal.listeners.CreatedAt;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreatedAt
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private boolean active = true;


}
