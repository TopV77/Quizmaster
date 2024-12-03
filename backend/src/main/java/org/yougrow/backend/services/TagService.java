package org.yougrow.backend.services;

import org.yougrow.backend.entities.Tag;
import org.yougrow.backend.entities.projection.Tag.TagDetail;
import org.yougrow.backend.entities.projection.Tag.TagShort;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<TagShort> getAllTagsShort();

    Optional<TagDetail> getTagDetailById(Long id);

    Optional<Tag> getTagById(Long id);

    Tag addTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteTag(Long id);

}
