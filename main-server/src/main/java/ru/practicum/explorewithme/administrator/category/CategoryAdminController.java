package ru.practicum.explorewithme.administrator.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryAdminService service;

    @PatchMapping
    public CategoryDto patchCategories(@Valid @RequestBody CategoryDto categoryDto) {
        System.out.println("PATCH/admin/categories");
        return service.patchCategories(categoryDto);
    }

    @PostMapping
    public CategoryDto postCategory(@RequestBody final CategoryDto categoryDto) {
        System.out.println("POST/admin/categories");
        return service.postCategory(categoryDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteCategories(@PathVariable("userId") long id) {
        System.out.println("DELETE/admin/categories");
        service.deleteCategories(id);
    }
}
