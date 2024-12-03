package org.yougrow.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yougrow.backend.entities.Category;
import org.yougrow.backend.entities.projection.Category.CategoryDetail;
import org.yougrow.backend.entities.projection.Category.CategoryShort;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<CategoryShort> findAllCategoryShortBy();

    Optional<CategoryDetail> findCategoryDetailById(Long id);
}
