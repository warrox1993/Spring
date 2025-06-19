package com.example.Decouverte_Spring_boot.api.models.product;

import lombok.Data;

@Data
public class ProductUpdateForm {
    private String name;
    private Boolean isPromote;
}
