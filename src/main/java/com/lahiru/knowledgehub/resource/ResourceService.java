package com.lahiru.knowledgehub.resource;

import com.lahiru.knowledgehub.collection.Collection;
import com.lahiru.knowledgehub.collection.CollectionRepository;
import com.lahiru.knowledgehub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final CollectionRepository collectionRepository;

    public ResourceResponse create(ResourceRequest request, User user) {
        Collection collection = collectionRepository
                .findByIdAndUserId(request.getCollectionId(), user.getId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));

        Resource resource = Resource.builder()
                .title(request.getTitle())
                .url(request.getUrl())
                .notes(request.getNotes())
                .resourceType(request.getResourceType())
                .collection(collection)
                .build();

        return toResponse(resourceRepository.save(resource));
    }

    public List<ResourceResponse> getAll(User user) {
        return resourceRepository.findByCollectionUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ResourceResponse getOne(UUID id, User user) {
        Resource resource = getOwnedResource(id, user);
        return toResponse(resource);
    }

    public ResourceResponse update(UUID id, ResourceRequest request, User user) {
        Resource resource = getOwnedResource(id, user);

        Collection collection = collectionRepository
                .findByIdAndUserId(request.getCollectionId(), user.getId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));

        resource.setTitle(request.getTitle());
        resource.setUrl(request.getUrl());
        resource.setNotes(request.getNotes());
        resource.setResourceType(request.getResourceType());
        resource.setCollection(collection);

        return toResponse(resourceRepository.save(resource));
    }

    public void delete(UUID id, User user) {
        Resource resource = getOwnedResource(id, user);
        resourceRepository.delete(resource);
    }

    private Resource getOwnedResource(UUID id, User user) {
        return resourceRepository
                .findByIdAndCollectionUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    private ResourceResponse toResponse(Resource resource) {
        return new ResourceResponse(
                resource.getId(),
                resource.getCollection().getId(),
                resource.getTitle(),
                resource.getUrl(),
                resource.getNotes(),
                resource.getResourceType()
        );
    }
}