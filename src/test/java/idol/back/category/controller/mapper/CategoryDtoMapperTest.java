package idol.back.category.controller.mapper;

import idol.back.category.application.domain.Category;
import idol.back.category.application.service.CategoryService;
import idol.back.category.controller.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CategoryDtoMapperTest {

    @Autowired CategoryService categoryService;
    @Autowired CategoryDtoMapper mapper;

    @Test
    void toDto() {
        String categoryName = "남자";
        Integer parentId = categoryService.create(categoryName, 0);
        String childCategoryName = "엑소";
        Integer parent2Id = categoryService.create(childCategoryName, parentId);
        String child2CategoryName = "백현";
        categoryService.create(child2CategoryName, parent2Id);
        Category category = categoryService.searchByCategoryId(parentId);
        CategoryDto result = mapper.toDto(category, category.toDtoList());
        log.info("result = {}", result);
    }


}