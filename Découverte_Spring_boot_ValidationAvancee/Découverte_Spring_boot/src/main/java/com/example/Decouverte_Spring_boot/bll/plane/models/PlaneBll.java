package com.example.Decouverte_Spring_boot.bll.plane.models;

import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaneBll {
    private Long id;
    private String imma;
    private String ownerName;
    private String typeName;

    public static PlaneBll fromEntity(PlaneEntity entity) {
        return PlaneBll.builder()
                .id(entity.getId())
                .imma(entity.getImma())
                .ownerName(entity.getOwner().getName())
                .typeName(entity.getType().getName())
                .build();
    }
}
