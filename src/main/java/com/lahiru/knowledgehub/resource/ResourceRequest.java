package com.lahiru.knowledgehub.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResourceRequest {

    @NotNull
    private UUID collectionId;

    @NotBlank
    private String title;

    private String url;

    private String notes;

    @NotNull
    private ResourceType resourceType;

    private List<UUID> tagIds;
}