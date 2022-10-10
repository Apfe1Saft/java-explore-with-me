package ru.practicum.explorewithme.authorized.event;

import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;

import java.util.List;

public interface EventAuthorizedService {
    List<EventShortDto> getEventsByUser(long userId, int from, int size);

    EventFullDto patchEventsByUser(UpdateEventRequest eventDto, long userId);

    EventFullDto postEventsByUser(NewEventDto eventDto, long userId);

    EventFullDto getEventByUser(long userId, long eventId);

    EventFullDto patchEventByUser(long userId, long eventId);

    List<ParticipantRequestDto> getRequestsByUser(long userId, long eventId);

    ParticipantRequestDto  confirmRequestsByUser(long userId, long eventId, long reqId);

    ParticipantRequestDto  rejectRequestsByUser(long userId, long eventId, long reqId);
}
