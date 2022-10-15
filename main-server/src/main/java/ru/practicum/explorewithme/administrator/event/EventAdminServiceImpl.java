package ru.practicum.explorewithme.administrator.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.ForbiddenException;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.event.AdminUpdateEventDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class EventAdminServiceImpl implements EventAdminService {
    private final EventRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Event> findEvents(long[] users, State[] states, long[] categories, LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd, int from, int size) {
        List<Event> events = Arrays.stream(users)
                .mapToObj(repository::findById)
                .filter(Objects::nonNull)
                .map(event -> {
                    for (long i : categories) {
                        if (i == event.getCategory().getId())
                            return event;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(event -> {
                    for (State state : states) {
                        if (state == event.getState())
                            return event;
                    }
                    return null;
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
                                    dateTime.isBefore(rangeEnd)) ? event : null;
                        }
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(from, size, Sort.by("id").descending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), events.size());
        events = new PageImpl<>(events.subList(start, end), pageable, events.size()).getContent();
        return events;
    }

    @Override
    public Event putEvent(long id, AdminUpdateEventDto eventDto) {
        Event event;
        if (repository.findById(id) == null) throw new NotFoundException("");
        event = repository.findById(id);
        event.setId(id);
        event.setAnnotation(eventDto.getAnnotation());
        event.setCategoryId(eventDto.getCategory());
        event.setCategory(categoryRepository.getById(event.getCategoryId()));
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());
        event.setPaid(eventDto.isPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.isRequestModeration());
        event.setTitle(eventDto.getTitle());
        repository.save(event);
        return event;
    }

    @Override
    public Event publishEvent(long id, boolean publish) {
        Event event = repository.findById(id);
        if (event.getState().equals(State.PENDING)) {
            if (publish) {
                event.setState(State.PUBLISHED);
                event.setPublishedOn(LocalDateTime.now());
            } else {
                event.setState(State.CANCELED);
            }
            repository.save(event);
            return event;
        }
        throw new ForbiddenException("");
    }

    @Override
    public Event findByIdEvent(long id) {
        return repository.findById(id);
    }
}
