package com.example.Decouverte_Spring_boot.bll.plane;

import com.example.Decouverte_Spring_boot.bll.plane.models.PlaneBll;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneTypeEntity;
import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import com.example.Decouverte_Spring_boot.dal.repositories.FiscalRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.PlaneRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.PlaneTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final FiscalRepository fiscalRepository;
    private final PlaneTypeRepository planeTypeRepository;

    @Override
    public PlaneBll addPlane(PlaneBll plane) {
        PlaneEntity planeEntity = new PlaneEntity();
        planeEntity.setImma(plane.getImma());

        FiscalEntity fiscalEntity = this.fiscalRepository
                .findOneByName(plane.getOwnerName())
                .orElseThrow(() -> new InvalidParameterException("Owner not found"));
        planeEntity.setOwner(fiscalEntity);
        planeEntity.setActive(true);

        PlaneTypeEntity planeTypeEntity = this.planeTypeRepository.findOneByName(plane.getTypeName())
                .orElseThrow(() -> new InvalidParameterException("Type not found"));
        planeEntity.setType(planeTypeEntity);

        planeRepository.save(planeEntity);

        return PlaneBll.builder()
                .id(planeEntity.getId())
                .imma(planeEntity.getImma())
                .ownerName(fiscalEntity.getName())
                .typeName(planeTypeEntity.getName())
                .build();
    }

    @Override
    public List<PlaneBll> getAllPlanes(boolean active) {
        return List.of();
    }

    @Override
    public PlaneBll getPlaneByImma(String imma) {
        return null;
    }

    @Override
    public PlaneBll changeImma(String oldImma, String newImma) {
        if (oldImma.equals(newImma)) throw new InvalidParameterException("Old and new imma are the same");
        PlaneEntity planeEntity = this.planeRepository.findAllByImmaLike(oldImma).getFirst();
        this.planeRepository.updateImmaById(planeEntity.getId(), newImma);

        return PlaneBll.builder()
                .id(planeEntity.getId())
                .imma(newImma)
                .build();
    }

    @Override
    public void deletePlane(String imma) {

    }
}
