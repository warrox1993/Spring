package com.example.Decouverte_Spring_boot.api.controllers;

import com.example.Decouverte_Spring_boot.api.exceptions.HttpException;
import com.example.Decouverte_Spring_boot.api.models.plane.PlaneCreateForm;
import com.example.Decouverte_Spring_boot.api.utils.BindingResultUtil;
import com.example.Decouverte_Spring_boot.bll.plane.PlaneService;
import com.example.Decouverte_Spring_boot.bll.plane.models.PlaneBll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;

    @GetMapping
    public ResponseEntity<Iterable<PlaneBll>> getAllPlanes() {
        return ResponseEntity.ok(this.planeService.getAllPlanes(true));
    }

    @PostMapping
    public ResponseEntity<PlaneBll> addPlane(
            @Validated() @RequestBody() PlaneCreateForm plane,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            var errors = BindingResultUtil.getErrorsFormBindingResult(bindingResult);
            throw new HttpException(HttpStatus.PRECONDITION_FAILED, "Form invalid", errors);
        }
        try {
            PlaneBll bll = PlaneBll.builder()
                    .imma(plane.imma())
                    .ownerName(plane.ownerName())
                    .typeName(plane.typeName())
                    .build();
            var planeBll = this.planeService.addPlane(bll);
            return ResponseEntity.status(HttpStatus.CREATED).body(planeBll);
        } catch (InvalidParameterException e) {
            throw new HttpException(HttpStatus.PRECONDITION_FAILED, e.getMessage());
        } catch (Exception e) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
