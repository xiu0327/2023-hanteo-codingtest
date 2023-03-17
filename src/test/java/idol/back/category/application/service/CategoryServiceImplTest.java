package idol.back.category.application.service;

import idol.back.board.application.domain.BoardType;
import idol.back.global.exceptions.BusinessException;
import idol.back.board.repository.BoardRepository;
import idol.back.category.application.domain.Category;
import idol.back.category.exceptions.CategoryExceptionType;
import idol.back.category.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CategoryServiceImplTest {

    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;
    @Autowired BoardRepository boardRepository;

    @AfterEach
    void clear(){
        categoryRepository.clear();
    }

    @Test
    void 부모_카테고리_생성_성공() {
        //given
        String categoryName = "남자";
        // when
        Integer categoryID = categoryService.create(categoryName, 0);
        // then
        Optional<Category> category = categoryRepository.findById(categoryID);
        assertThat(category.isPresent()).isTrue();
        assertThat(category.get().getName()).isEqualTo(categoryName);
    }

    @Test
    void 하위_카테고리_생성_성공() {
        // given
        String categoryName = "남자";
        Integer parentId = categoryService.create(categoryName, 0);
        String childCategoryName = "엑소";
        // when
        Integer childId = categoryService.create(childCategoryName, parentId);
        // then
        Optional<Category> parentCategory = categoryRepository.findById(parentId);
        Optional<Category> childCategory = categoryRepository.findById(childId);
        assertThat(parentCategory.isPresent()).isTrue();
        assertThat(childCategory.isPresent()).isTrue();

    }

    @Test
    void 익명_카테고리_생성() {
        // given
        String categoryName = "남자";
        Integer parentId = categoryService.create(categoryName, 0);
        String childCategoryName = "익명게시판";
        // when
        Integer childId = categoryService.create(childCategoryName, parentId);
        // then
        Optional<Category> parentCategory = categoryRepository.findById(parentId);
        Optional<Category> childCategory = categoryRepository.findById(childId);
        assertThat(parentCategory.isPresent()).isTrue();
        assertThat(childCategory.isPresent()).isTrue();
        assertThat(childCategory.get().getBoardId()).isEqualTo(boardRepository.findBoardIdByType(BoardType.ANONYMOUS));
    }

    @Test
    void 하위_카테고리_생성_실패() {
        // given
        String categoryName = "블랙핑크";
        Integer notExistParentId = 2;
        // when & then
        assertThrows(BusinessException.class, () -> {
            try{
                categoryService.create(categoryName, notExistParentId);
            } catch (BusinessException e){
                log.info(e.getMessage()); // 존재하지 않는 부모 카테고리 입니다.
                assertThat(e.getExceptionType()).isEqualTo(CategoryExceptionType.NON_EXISTENT_PARENT);
                throw e;
            }
        });
    }

    @Test
    void 카테고리_검색_by_Id() {
        //given
        String categoryName = "남자";
        Integer categoryId = categoryService.create(categoryName, 0);
        // when
        Optional<Category> result = categoryRepository.findById(categoryId);
        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(categoryName);
    }

    @Test
    void 카테고리_검색_by_Name() {
        //given
        String categoryName = "남자";
        Integer categoryId = categoryService.create(categoryName, 0);
        // when
        Optional<Category> result = categoryRepository.findByName(categoryName);
        // then
        assertThat(result.isPresent()).isTrue();
    }
}