package idol.back.category.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private List<CategoryDto> children;
    private Integer boardId;

    public CategoryDto(String name, Integer boardId) {
        this.name = name;
        this.boardId = boardId;
        this.children = new ArrayList<>();
    }

    public void addChild(CategoryDto child){
        children.add(child);
    }
}
