package com.lahiru.knowledgehub.collection;

import com.lahiru.knowledgehub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public CollectionResponse create(CollectionRequest request, User user) {
        Collection collection = Collection.builder()
                .name(request.getName())
                .description(request.getDescription())
                .user(user)
                .build();

        return toResponse(collectionRepository.save(collection));
    }

    public List<CollectionResponse> getAll(User user) {
        return collectionRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CollectionResponse getOne(UUID id, User user) {
        Collection collection = getOwnedCollection(id, user);
        return toResponse(collection);
    }

    public CollectionResponse update(UUID id, CollectionRequest request, User user) {
        Collection collection = getOwnedCollection(id, user);

        collection.setName(request.getName());
        collection.setDescription(request.getDescription());

        return toResponse(collectionRepository.save(collection));
    }

    public void delete(UUID id, User user) {
        Collection collection = getOwnedCollection(id, user);
        collectionRepository.delete(collection);
    }

    private Collection getOwnedCollection(UUID id, User user) {
        return collectionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));
    }

    private CollectionResponse toResponse(Collection collection) {
        return new CollectionResponse(
                collection.getId(),
                collection.getName(),
                collection.getDescription()
        );
    }
}