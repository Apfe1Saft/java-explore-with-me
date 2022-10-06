package ru.practicum.explorewithme.opened;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.Sort;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.NewEventDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class OpenedController {
    private final OpenedService service;

    @GetMapping("/events")
    public List<NewEventDto> getEventsDto(@RequestParam(required = false) String text,
                                          @RequestParam(required = false) long[] categories,
                                          @RequestParam(required = false) Boolean paid,
                                          @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                          @RequestParam(required = false)
                                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                          @RequestParam(defaultValue = "false") boolean onlyAvailable,
                                          @RequestParam(required = false) Sort sort,
                                          @Valid @PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                          @Valid @Positive @RequestParam(defaultValue = "10") int size,
                                          HttpServletRequest request) {
        System.out.println("/events");
        return service.getEventsDto(text,categories,paid,rangeStart,rangeEnd,onlyAvailable,sort,from,size,request);
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") long id,HttpServletRequest request) {
        System.out.println("/events/{id}");
        return service.getEvent(id,request);
    }

    @GetMapping("/categories")
    public List<Category> getCategories(@RequestParam(defaultValue = "0") int from,
                                         @RequestParam(defaultValue = "10") int size) {
        System.out.println("/events/categories");
        return service.getCategories(from,size);
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable("id") long id) {
        System.out.println("/events/categories/id");
        return service.getCategory(id);
    }

    @GetMapping("/compilation")
    public List<Compilation> getCompilations(@RequestParam(required = false) boolean pinned,
                                             @RequestParam(defaultValue = "0") int from,
                                             @RequestParam(defaultValue = "10") int size) {
        System.out.println("/events/compilation");
        return service.getCompilations(pinned,from,size);
    }

    @GetMapping("/compilation/{id}")
    public Compilation getCompilation(@PathVariable("id") long id) {
        System.out.println("/events/compilation/{id}");
        return service.getCompilation(id);
    }
}
