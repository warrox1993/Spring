package com.example.Decouverte_Spring_boot.bll.plane;

import com.example.Decouverte_Spring_boot.bll.plane.models.PlaneBll;

import java.util.List;

public interface PlaneService {
    PlaneBll addPlane(PlaneBll plane);

    List<PlaneBll> getAllPlanes(boolean active);

    PlaneBll getPlaneByImma(String imma);

    PlaneBll changeImma(String oldImma, String newImma);

    void deletePlane(String imma);
}
