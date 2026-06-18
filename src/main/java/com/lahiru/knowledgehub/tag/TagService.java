package com.lahiru.knowledgehub.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public TagResponse create(TagRequest request) {
        String normalizedName = request.getName().trim().toLowerCase();

        Tag existingTag = tagRepository.findByName(normalizedName)
                .orElse(null);

        if (existingTag != null) {
            return toResponse(existingTag);
        }

        Tag tag = Tag.builder()
                .name(normalizedName)
                .build();

        return toResponse(tagRepository.save(tag));
    }

    public List<TagResponse> getAll() {
        return tagRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TagResponse getOne(UUID id) {
        Tag tag = getTag(id);
        return toResponse(tag);
    }

    public TagResponse update(UUID id, TagRequest request) {
        Tag tag = getTag(id);

        tag.setName(request.getName().trim().toLowerCase());

        return toResponse(tagRepository.save(tag));
    }

    public void delete(UUID id) {
        Tag tag = getTag(id);
        tagRepository.delete(tag);
    }

    private Tag getTag(UUID id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    private TagResponse toResponse(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName()
        );
    }
}