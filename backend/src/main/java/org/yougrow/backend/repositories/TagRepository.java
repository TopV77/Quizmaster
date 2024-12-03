package org.yougrow.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yougrow.backend.entities.Tag;
import org.yougrow.backend.entities.projection.Tag.TagDetail;
import org.yougrow.backend.entities.projection.Tag.TagShort;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<TagShort> findAllTagsShortBy();

    Optional<TagDetail> findTagDetailById(Long id);
}
