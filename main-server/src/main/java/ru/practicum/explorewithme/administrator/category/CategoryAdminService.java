package ru.practicum.explorewithme.administrator.category;

import ru.practicum.explorewithme.model.category.CategoryDto;

public interface CategoryAdminService {
    CategoryDto patchCategories(CategoryDto categoryDto);

    CategoryDto postCategory(CategoryDto categoryDto);

    void deleteCategories(long id);
}
