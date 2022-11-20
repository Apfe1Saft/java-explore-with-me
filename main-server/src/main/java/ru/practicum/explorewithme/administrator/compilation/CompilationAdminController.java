package ru.practicum.explorewithme.administrator.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/admin/compilations")
@Slf4j
@RequiredArgsConstructor
public class CompilationAdminController {
    private final CompilationAdminService service;

    @PostMapping//
    public CompilationDto postCompilation(@RequestBody(required = false) final NewCompilationDto newCompilationDto) {
        log.debug("Admin: POST /admin/compilation request");
        NewCompilationDto compilationDto = new NewCompilationDto();
        if (newCompilationDto == null) {
            compilationDto.setEvents(new ArrayList<>());
            compilationDto.setPinned(false);
            compilationDto.setTitle("");
        } else {
            compilationDto = newCompilationDto;
        }
        return service.postCompilation(compilationDto);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable("compId") long compId) {
        log.debug("Admin: DELETE /admin/categories/{compId} request with compId: " + compId);
        service.deleteCompilation(compId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public Compilation deleteEventFromCompilation(@PathVariable("compId") long compId, @PathVariable("eventId") long eventId) {
        log.debug("Admin: DELETE /admin/categories request");
        service.deleteEventFromCompilation(compId, eventId);
        return new Compilation();
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void patchCompilation(@PathVariable("compId") long compId, @PathVariable("eventId") long eventId) {
        log.debug("Admin: PATCH /admin/categories request/{compId}/events/{eventId} with compId: "
                + compId + " and eventId: " + eventId);
        service.patchCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    public void deleteMainCompilation(@PathVariable("compId") long compId) {
        log.debug("Admin: DELETE /admin/categories/{compId}/pin request with compId: " + compId);
        service.deleteMainCompilation(compId);
    }

    @PatchMapping("/{compId}/pin")
    public void patchMainCompilation(@PathVariable("compId") long compId) {
        log.debug("Admin: PATCH /admin/categories/{compId}/pin request with compId: " + compId);
        service.patchMainCompilation(compId);
    }
}
