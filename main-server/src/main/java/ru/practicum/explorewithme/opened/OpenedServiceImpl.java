package ru.practicum.explorewithme.opened;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.client.EndpointHit;
import ru.practicum.explorewithme.client.EventClient;
import ru.practicum.explorewithme.model.Sort;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventMapper;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.CompilationRepository;
import ru.practicum.explorewithme.repository.EventRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class OpenedServiceImpl implements OpenedService {
    private final EventRepository eventOpenedRepository;
    private final CompilationRepository compilationOpenedRepository;
    private final CategoryRepository categoryOpenedRepository;
    private final EventClient client;

    @Override
    public Event getEvent(long id, HttpServletRequest request) {
        client.saveRequest(new EndpointHit(0L, "explore-with-me", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now()));
        return eventOpenedRepository.findById(Long.parseLong(String.valueOf(id)));
    }

    @Override
    public List<Category> getCategories(int from, int size) {
        List<Category> categories = categoryOpenedRepository.findAll();
        Pageable pageable = PageRequest.of(from, size, org.springframework.data.domain.Sort.by("id").descending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), categories.size());
        categories = new PageImpl<>(categories.subList(start, end), pageable, categories.size()).getContent();
        return categories;
    }

    @Override
    public List<Compilation> getCompilations(boolean pinned, int from, int size) {
        List<Compilation> compilations = compilationOpenedRepository.findAll();
        compilations = compilations.stream().map(compilation -> {
            if (compilation.isPinned() == pinned) {
                ;
                return compilation;
            }
            return null;
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return compilations;
    }

    @Override
    public Category getCategory(long id) {
        if (categoryOpenedRepository.findById(id).isEmpty()) return null;
        return categoryOpenedRepository.findById(Long.parseLong(String.valueOf(id))).get();
    }

    @Override
    public Compilation getCompilation(long id) {
        return compilationOpenedRepository.findById(Long.parseLong(String.valueOf(id))).get();
    }

    @Override
    public List<Event> sortEvents(String text, long[] categories, boolean paid, boolean onlyAvailable
            , LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        List<Event> events = eventOpenedRepository.findAll()
                .stream().map(event -> {
                    for (long i : categories) {
                        if (i == event.getCategory().getId()) {
                            return event;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(event -> {
                    if (event.isPaid() == paid) {
                        return event;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(event -> {
                    if (onlyAvailable) {

                        if (event.getConfirmedRequests() < event.getParticipantLimit()) {
                            return event;
                        }
                        return null;
                    } else {
                        return event;
                    }
                })
                .filter(Objects::nonNull)
                .map(event -> {
                    LocalDateTime dateTime = event.getEventDate();
                    if (rangeEnd == null && rangeStart == null) {
                        return event;
                    } else {
                        if (rangeEnd != null && rangeStart == null) {
                            return dateTime.isBefore(rangeEnd) ? event : null;
                        } else if (rangeStart != null && rangeEnd == null) {
                            return dateTime.isAfter(rangeStart) ? event : null;
                        } else {
                            return (dateTime.isAfter(rangeStart) &&
                                    dateTime.isBefore(rangeEnd)) ? event : event;
                        }
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Event> eventsWithText = new ArrayList<>();
        for (Event event : events) {
            if (event.getDescription().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
                eventsWithText.add(event);
            if (event.getAnnotation().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
                eventsWithText.add(event);
        }
        return eventsWithText;

    }

    @Override
    public List<EventShortDto> getEventsDto(String text, long[] categories, Boolean paid, LocalDateTime rangeStart,
                                            LocalDateTime rangeEnd, boolean onlyAvailable, Sort sort, int from,
                                            int size, HttpServletRequest request) {
        List<Event> events = sortEvents(text, categories, paid, onlyAvailable, rangeStart, rangeEnd);
        Pageable pageable = PageRequest.of(from, size, org.springframework.data.domain.Sort.by("id").descending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), events.size());
        events = new PageImpl<>(events.subList(start, end), pageable, events.size()).getContent();
        List<EventShortDto> newEventDtos = new ArrayList<>();
        for (Event event : events) {
            newEventDtos.add(EventMapper.toEventShortDto(event));
        }
        client.saveRequest(new EndpointHit(0L, "explore-with-me", request.getRequestURI(), request.getRemoteAddr(), LocalDateTime.now()));
        return newEventDtos;
    }
}
