package ru.practicum.explorewithme.administrator.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.category.CategoryMapper;
import ru.practicum.explorewithme.repository.CategoryRepository;

@Component
@RequiredArgsConstructor
@Getter
public class CategoryAdminServiceImpl implements CategoryAdminService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto patchCategories(CategoryDto categoryDto) {
        repository.save(CategoryMapper.toCategory(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.toCategory(categoryDto);
        repository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public void deleteCategories(long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("");
        }
        repository.deleteById(id);
    }
}
