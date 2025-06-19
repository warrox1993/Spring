package com.example.Decouverte_Spring_boot.bll.plane;

import com.example.Decouverte_Spring_boot.bll.plane.models.PlaneBll;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity_;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneTypeEntity;
import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import com.example.Decouverte_Spring_boot.dal.repositories.FiscalRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.PlaneRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.PlaneTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Stream;

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
        planeEntity.setOwnerId(fiscalEntity.getId());
        planeEntity.setActive(true);

        PlaneTypeEntity planeTypeEntity = this.planeTypeRepository.findOneByName(plane.getTypeName())
                .orElseThrow(() -> new InvalidParameterException("Type not found"));
        planeEntity.setTypeId(planeTypeEntity.getId());

        planeRepository.save(planeEntity);

        return PlaneBll.fromEntity(planeEntity);
    }

    @Override
    public List<PlaneBll> getAllPlanes(boolean active) {
        return this.planeRepository.findAll(PlaneRepository.byActive(active)).stream()
                .map(PlaneBll::fromEntity)
                .toList();
    }

    public List<PlaneBll> getAllPlanesByOwner(String owner) {
        return this.planeRepository.findAll(PlaneRepository.withOwnerByName(owner))
                .stream()
                .map(PlaneBll::fromEntity)
                .toList();
    }

    @Override
    public PlaneBll getPlaneByImma(String imma) {
        return this.planeRepository.findOne(PlaneRepository.byImma(imma))
                .map(PlaneBll::fromEntity)
                .orElseThrow(() -> new InvalidParameterException("Plane not found"));
    }

    public PlaneBll changeImma(Long id, String newImma) {
        PlaneEntity planeEntity = this.planeRepository.findOne(PlaneRepository.byId(id))
                .orElseThrow(() -> new InvalidParameterException("Plane not found"));

        return changeImma(planeEntity, newImma);
    }

    @Override
    public PlaneBll changeImma(String oldImma, String newImma) {
        PlaneEntity planeEntity = this.planeRepository.findOne(PlaneRepository.byImma(oldImma))
                .orElseThrow(() -> new InvalidParameterException("Plane not found"));

        return changeImma(planeEntity, newImma);
    }

    private PlaneBll changeImma(PlaneEntity planeEntity, String newImma) {
        if (newImma.equals(planeEntity.getImma()))
            throw new InvalidParameterException("New imma is the same as the old one");
        planeEntity.setImma(newImma);
        this.planeRepository.save(planeEntity);
        return PlaneBll.fromEntity(planeEntity);
    }

    @Override
    public void deletePlane(String imma) {
        this.planeRepository.deleteByImma(imma);
    }
}
