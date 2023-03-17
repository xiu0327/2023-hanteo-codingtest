package idol.back.category.repository;

import idol.back.category.application.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryMemoryRepository implements CategoryRepository{

    private final Map<Integer, Category> categories = new HashMap<>();
    private static Integer sequence = 0;


    @Override
    public Integer save(Category category) {
        categories.put(++sequence, category);
        category.setId(sequence);
        Integer parentId = category.getParentId();
        if (parentId != 0){
            Category parent = categories.get(parentId);
            parent.addChild(category);
        }
        return category.getId();
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {
        return Optional.ofNullable(categories.get(categoryId));
    }

    @Override
    public Optional<Category> findByName(String categoryName) {
        return categories.values().stream()
                .filter(category -> category.getName().equals(categoryName))
                .findAny();
    }

    @Override
    public Map<Integer, Category> findAll() {
        return categories;
    }

    @Override
    public void clear() {
        categories.clear();
    }
}
