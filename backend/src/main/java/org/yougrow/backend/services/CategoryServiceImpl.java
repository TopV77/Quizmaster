package org.yougrow.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yougrow.backend.entities.Category;
import org.yougrow.backend.entities.projection.Category.CategoryDetail;
import org.yougrow.backend.entities.projection.Category.CategoryShort;
import org.yougrow.backend.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryShort> getAllCategoriesShort() {
        return categoryRepository.findAllCategoryShortBy();
    }

    @Override
    public Optional<CategoryDetail> getCategoryDetailById(Long id) {
        return categoryRepository.findCategoryDetailById(id);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
