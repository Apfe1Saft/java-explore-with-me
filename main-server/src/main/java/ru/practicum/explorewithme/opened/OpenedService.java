package ru.practicum.explorewithme.opened;

import ru.practicum.explorewithme.model.Sort;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface OpenedService {
    Event getEvent(long id, HttpServletRequest request);

    Category getCategory(long id);

    Compilation getCompilation(long id);

    List<Event> sortEvents(String text, long[] categories, boolean paid, boolean
            onlyAvailable, LocalDateTime rangeStart, LocalDateTime rangeEnd);

    List<EventShortDto> getEventsDto(String text, long[] categories, Boolean paid,
                                     LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                     boolean onlyAvailable, Sort sort, int from, int size,
                                     HttpServletRequest request);

    List<Category> getCategories(int from, int size);

    List<Compilation> getCompilations(boolean pinned, int from, int size);
}
