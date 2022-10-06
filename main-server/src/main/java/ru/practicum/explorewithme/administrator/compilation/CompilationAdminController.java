package ru.practicum.explorewithme.administrator.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.administrator.category.CategoryAdminService;
import ru.practicum.explorewithme.administrator.exception.ForbiddenException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/compilations")
@Slf4j
@RequiredArgsConstructor
public class CompilationAdminController {
    private final CompilationAdminService service;
    @PostMapping
    public CompilationDto postCompilation(@RequestBody final NewCompilationDto newCompilationDto) {
        System.out.println(newCompilationDto);
        return service.postCompilation(newCompilationDto);
    }
    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable("compId") long compId){
        service.deleteCompilation(compId);
    }
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable("compId") long compId,@PathVariable("eventId") long eventId){
        service.deleteEventFromCompilation(compId,eventId);
    }
    @PatchMapping("/{compId}/events/{eventId}")
    public void patchCompilation(@PathVariable("compId") long compId,@PathVariable("eventId") long eventId){
        service.patchCompilation(compId,eventId);
    }
    @DeleteMapping("/{compId}/pin")
    public void deleteMainCompilation(@PathVariable("compId") long compId){
        service.deleteMainCompilation(compId);
    }
    @PatchMapping("/{compId}/pin")
    public void patchMainCompilation(@PathVariable("compId") long compId){
        service.patchMainCompilation(compId);
    }
    public void nullChecker(Object object){
        if(object == null) throw new ForbiddenException("Подборка должны содержать поля: id, title, pinned, events");
    }
}
