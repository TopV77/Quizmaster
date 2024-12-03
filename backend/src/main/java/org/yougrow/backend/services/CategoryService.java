package org.yougrow.backend.services;

import org.yougrow.backend.entities.Category;
import org.yougrow.backend.entities.projection.Category.CategoryDetail;
import org.yougrow.backend.entities.projection.Category.CategoryShort;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryShort> getAllCategoriesShort();

    Optional<CategoryDetail> getCategoryDetailById(Long id);

    Optional<Category> getCategoryById(Long id);

    Category addCategory(Category tag);

    Category updateCategory(Category tag);

    void deleteCategory(Long id);
}
