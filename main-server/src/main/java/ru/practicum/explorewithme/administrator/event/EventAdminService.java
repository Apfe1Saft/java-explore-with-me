package ru.practicum.explorewithme.administrator.event;

import ru.practicum.explorewithme.model.event.AdminUpdateEventDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventAdminService {

    List<Event> findEvents(long[] users, State[] states, long[] categories, LocalDateTime rangeStart,
                           LocalDateTime rangeEnd, int from, int size);

    Event putEvent(long eventId, AdminUpdateEventDto eventDto);

    Event publishEvent(long eventId, boolean publish);

    Event findByIdEvent(long eventId);
}
