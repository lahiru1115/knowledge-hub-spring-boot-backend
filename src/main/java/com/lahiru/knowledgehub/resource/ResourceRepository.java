package com.lahiru.knowledgehub.resource;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByCollectionUserId(UUID userId);

    Optional<Resource> findByIdAndCollectionUserId(UUID id, UUID userId);
}