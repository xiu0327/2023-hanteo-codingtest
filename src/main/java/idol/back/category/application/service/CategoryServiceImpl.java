package idol.back.category.application.service;

import idol.back.board.application.domain.Board;
import idol.back.global.exceptions.BusinessException;
import idol.back.board.repository.BoardRepository;
import idol.back.category.application.domain.Category;
import idol.back.category.exceptions.CategoryExceptionType;
import idol.back.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @Override
    public Integer create(String categoryName, Integer parentId) {
        existParentId(parentId);
        Category category = new Category(categoryName, parentId);
        Integer categoryId = categoryRepository.save(category);
        Board board = new Board(categoryId, categoryName);
        Integer boardId = boardRepository.save(board);
        category.setBoardId(boardId);
        return category.getId();
    }

    private void existParentId(Integer parentId){
        if (parentId != 0){
            categoryRepository.findById(parentId)
                    .orElseThrow(() -> new BusinessException(CategoryExceptionType.NON_EXISTENT_PARENT));
        }
    }

    @Override
    public Category searchByCategoryId(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(CategoryExceptionType.NOT_FOUND_CATEGORY));
    }

    @Override
    public Category searchByCategoryName(String categoryName) {
        return categoryRepository.findAll().values().stream()
                .filter(category -> category.getName().equals(categoryName))
                .findAny()
                .orElseThrow(() -> new BusinessException(CategoryExceptionType.NOT_FOUND_CATEGORY));
    }

}
