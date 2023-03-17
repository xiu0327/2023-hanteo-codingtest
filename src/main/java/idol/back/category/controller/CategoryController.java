package idol.back.category.controller;

import idol.back.category.application.domain.Category;
import idol.back.category.application.service.CategoryService;
import idol.back.category.controller.dto.CategoryDto;
import idol.back.category.controller.mapper.CategoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDtoMapper mapper;

    @GetMapping("/api/category")
    public ResponseEntity<Integer> register(@RequestParam("name") String categoryName,
                                   @RequestParam("parentId") Integer parentId){
        Integer categoryId = categoryService.create(categoryName, parentId);
        return new ResponseEntity<>(categoryId, HttpStatus.CREATED);
    }

    @GetMapping("/api/category/search/{categoryId}")
    public CategoryDto searchById(@PathVariable("categoryId") Integer categoryId){
        Category result = categoryService.searchByCategoryId(categoryId);
        return mapper.toDto(result, result.toDtoList());
    }

    @GetMapping("/api/category/search")
    public CategoryDto searchByName(@RequestParam("categoryName") String categoryName){
        Category result = categoryService.searchByCategoryName(categoryName);
        return mapper.toDto(result, result.toDtoList());
    }

}
