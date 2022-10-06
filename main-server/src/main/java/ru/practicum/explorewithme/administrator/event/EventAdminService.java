package ru.practicum.explorewithme.administrator.event;

import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.event.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventAdminService {

    List<Event> findEvents(long[] users, State[] states, long[] categories, LocalDateTime rangeStart,
                              LocalDateTime rangeEnd, int from, int size);
    Event putEvent(long id, Event event);
    Event publishEvent(long id, boolean publish);
    Event findByIdEvent(long id);
}
