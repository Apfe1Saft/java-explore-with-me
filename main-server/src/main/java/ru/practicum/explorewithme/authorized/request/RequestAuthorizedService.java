package ru.practicum.explorewithme.authorized.request;

import ru.practicum.explorewithme.model.request.ParticipantRequestDto;

import java.util.List;

public interface RequestAuthorizedService {
    List<ParticipantRequestDto> getUserRequestsToEvents(long userId);

    ParticipantRequestDto postUserRequestsToEvents(long userId, long eventId);

    ParticipantRequestDto cancelUserRequestsToEvents(long userId, long requestId);
}
