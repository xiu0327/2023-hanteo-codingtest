package idol.back.category.repository;

import idol.back.category.application.domain.Category;

import java.util.Map;
import java.util.Optional;

public interface CategoryRepository {
    Integer save(Category category);
    Optional<Category> findById(Integer categoryId);
    Optional<Category> findByName(String categoryName);
    Map<Integer, Category> findAll();
    void clear();
}
