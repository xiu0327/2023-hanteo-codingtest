package idol.back.category.controller.mapper;

import idol.back.category.application.domain.Category;
import idol.back.category.controller.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);

    CategoryDto toDto(Category category, List<CategoryDto> children);
}
