package com.example.Decouverte_Spring_boot.bll.plane.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaneBll {
    private Long id;
    private String imma;
    private String ownerName;
    private String typeName;
}
