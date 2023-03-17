package idol.back.category.application.domain;

import idol.back.category.controller.dto.CategoryDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<Category> children;
    private Integer boardId;

    public Category(String name, Integer parentId) {
        this.name = name;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    /* 비즈니스 로직 */

    public void addChild(Category child){
        this.children.add(child);
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setBoardId(Integer boardId){
        this.boardId = boardId;
    }

    public CategoryDto toDto(){
        CategoryDto dto = new CategoryDto(this.name, this.boardId);
        for (Category child : this.children) {
            dto.addChild(child.toDto());
        }
        return dto;
    }

    public List<CategoryDto> toDtoList(){
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category child : this.children) {
            dtos.add(child.toDto());
        }
        return dtos;
    }
}
