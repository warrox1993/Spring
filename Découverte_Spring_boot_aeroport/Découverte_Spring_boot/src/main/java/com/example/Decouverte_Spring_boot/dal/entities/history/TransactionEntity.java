package com.example.Decouverte_Spring_boot.dal.entities.history;

import com.example.Decouverte_Spring_boot.dal.entities.AbstractEntity;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "Transaction")
@Data
public class TransactionEntity extends AbstractEntity {
    private LocalDate date;
    private double amount;

    private Long planeId;
    private Long sellerId;
    private Long buyerId;

    @ManyToOne
    private PlaneEntity plane;
    @ManyToOne
    private FiscalEntity buyer;
    @ManyToOne
    private FiscalEntity seller;
}
