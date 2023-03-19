package idol.back.category.controller.mapper;

import idol.back.category.application.domain.Category;
import idol.back.category.controller.dto.CategoryDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T01:35:53+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15 (JetBrains s.r.o.)"
)
@Component
public class CategoryDtoMapperImpl implements CategoryDtoMapper {

    @Override
    public CategoryDto toDto(Category category, List<CategoryDto> children) {
        if ( category == null && children == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        if ( category != null ) {
            categoryDto.setName( category.getName() );
            categoryDto.setChildren( categoryListToCategoryDtoList( category.getChildren() ) );
            categoryDto.setBoardId( category.getBoardId() );
        }

        return categoryDto;
    }

    protected List<CategoryDto> categoryListToCategoryDtoList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDto> list1 = new ArrayList<CategoryDto>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDto( category ) );
        }

        return list1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName( category.getName() );
        categoryDto.setChildren( categoryListToCategoryDtoList( category.getChildren() ) );
        categoryDto.setBoardId( category.getBoardId() );

        return categoryDto;
    }
}
