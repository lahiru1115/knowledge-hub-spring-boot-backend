package com.lahiru.knowledgehub.collection;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionRequest {

    @NotBlank
    private String name;

    private String description;
}