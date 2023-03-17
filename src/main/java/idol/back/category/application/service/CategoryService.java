package idol.back.category.application.service;

import idol.back.category.application.domain.Category;

public interface CategoryService {
    Integer create(String categoryName, Integer parentId);
    Category searchByCategoryId(Integer categoryId);
    Category searchByCategoryName(String categoryName);
}
