package ru.practicum.explorewithme.administrator.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.category.CategoryDto;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/categories")
@Slf4j//
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryAdminService service;

    @PatchMapping
    public CategoryDto patchCategories(@Valid @RequestBody CategoryDto categoryDto) {
        log.debug("Admin: PATCH /admin/categories request");
        return service.patchCategories(categoryDto);
    }

    @PostMapping
    public CategoryDto postCategory(@RequestBody final CategoryDto categoryDto) {
        log.debug("Admin: POST /admin/categories request");
        return service.postCategory(categoryDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteCategories(@PathVariable("userId") long id) {
        log.debug("Admin: DELETE /admin/categories request");
        service.deleteCategories(id);
    }
}
