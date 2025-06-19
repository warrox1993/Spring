package com.example.Decouverte_Spring_boot.dal.entities.fiscals;

import com.example.Decouverte_Spring_boot.dal.entities.AbstractEntity;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity(name = "Fiscal")
@Data
@Inheritance(strategy = JOINED)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FiscalEntity extends AbstractEntity {
    private String name;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    public List<PlaneEntity> planes;
}
