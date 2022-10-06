package ru.practicum.explorewithme.administrator.category;

import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.model.user.UserDto;

import java.util.List;

public interface CategoryAdminService {
    CategoryDto patchCategories(CategoryDto categoryDto);
    CategoryDto postCategory(CategoryDto categoryDto);
    void deleteCategories(long id);
}
