package org.yougrow.backend.services;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.yougrow.backend.entities.Tag;
import org.yougrow.backend.entities.projection.Tag.TagDetail;
import org.yougrow.backend.entities.projection.Tag.TagShort;
import org.yougrow.backend.repositories.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagShort> getAllTagsShort() {
        return tagRepository.findAllTagsShortBy();
    }

    @Override
    public Optional<TagDetail> getTagDetailById(Long id) {
        return tagRepository.findTagDetailById(id);
    }

    @Override
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
