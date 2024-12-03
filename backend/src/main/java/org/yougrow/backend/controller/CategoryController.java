package org.yougrow.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yougrow.backend.entities.Category;
import org.yougrow.backend.entities.Password;
import org.yougrow.backend.entities.Token;
import org.yougrow.backend.entities.projection.Category.CategoryDetail;
import org.yougrow.backend.entities.projection.Category.CategoryShort;
import org.yougrow.backend.services.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<CategoryShort>> getAllCategories() {
        return ok(categoryService.getAllCategoriesShort());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetail> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryDetailById(id)
                .map(category -> ok(category))
                .orElse(badRequest().build());
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        if (category.getId() != null) {
            return badRequest().build();
        }

        category.setPassword(passwordEncoder.encode(category.getPassword()));
        Category savedCategory = categoryService.addCategory(category);
        return ok(savedCategory);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category) {
        Optional<Category> existingCategory = categoryService.getCategoryById(category.getId());
        if (category.getId() == null || existingCategory.isEmpty()) {
            return badRequest().build();
        }

        if (category.getPassword() == null) {
            category.setPassword(existingCategory.get().getPassword());
        } else {
            category.setPassword(passwordEncoder.encode(category.getPassword()));
        }

        Category savedCategory = categoryService.updateCategory(category);
        return ok(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ok().build();
    }

    @PostMapping("/{id}/check-password")
    public ResponseEntity<Map<String, String>> checkPassword(@PathVariable Long id, @RequestBody Password request) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = passwordEncoder.matches(request.getPassword(), optionalCategory.get().getPassword());

        if (matches) {
            return ok(Map.of("authToken", optionalCategory.get().getAuthToken()));
        } else {
            return ok(null);
        }
    }

    @PostMapping("/{id}/check-token")
    public ResponseEntity<Boolean> checkToken(@PathVariable Long id, @RequestBody Token request) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = Objects.equals(request.getToken(), optionalCategory.get().getAuthToken());

        if (matches) {
            return ok(true);
        } else {
            return ok(false);
        }
    }
}
