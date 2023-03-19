package idol.back.category.application.service;

import idol.back.board.application.domain.Board;
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
        // given & when
        Integer root = categoryService.create("남자", 0);
        Integer exo = categoryService.create("엑소", root);
        Integer father = categoryService.create("첸", exo);
        Integer bh = categoryService.create("백현", exo);
        // then
        Optional<Category> exoCategory = categoryRepository.findById(exo);
        Optional<Category> father_category = categoryRepository.findById(father);
        Optional<Category> bh_category = categoryRepository.findById(bh);
        /* 값이 존재하는지 */
        assertThat(exoCategory.isPresent()).isTrue();
        assertThat(father_category.isPresent()).isTrue();
        assertThat(bh_category.isPresent()).isTrue();
        /* 부모 카테고리에 자식 카테고리가 제대로 저장 되었는지 */
        assertThat(father_category.get()).isIn(exoCategory.get().getChildren());
        assertThat(bh_category.get()).isIn(exoCategory.get().getChildren());
        /* 익명게시판이 아니면 서로 다른 게시판 번호가 부여되었는지 */
        assertThat(father_category.get().getBoardId()).isNotEqualTo(bh_category.get().getBoardId());
        /* 자식들의 부모 카테고리 번호가 같은지 */
        assertThat(father_category.get().getParentId()).isEqualTo(bh_category.get().getParentId());
    }

    @Test
    void 익명_게시판_생성_성공() {
        // given
        Integer root1 = categoryService.create("남자", 0);
        Integer p2 = categoryService.create("엑소", root1);
        Integer root2 = categoryService.create("여자", 0);
        Integer p4 = categoryService.create("뉴진스", root2);
        // when
        Integer c1 = categoryService.create("익명게시판", p2);
        Integer c2 = categoryService.create("익명게시판", p4);
        // then
        Optional<Category> category1 = categoryRepository.findById(c1);
        Optional<Category> category2 = categoryRepository.findById(c2);
        assertThat(category1.isPresent()).isTrue();
        assertThat(category2.isPresent()).isTrue();
        /* 익명게시판이면, 부모 상관없이 같은 게시글 번호를 부여받았는지 */
        assertThat(category1.get().getBoardId()).isEqualTo(category2.get().getBoardId());
        assertThat(category1.get().getParentId()).isNotEqualTo(category2.get().getParentId());
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
        categoryService.create(categoryName, 0);
        // when
        Optional<Category> result = categoryRepository.findByName(categoryName);
        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(categoryName);
    }
}