package ru.practicum.explorewithme.authorized.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.ForbiddenException;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;
import ru.practicum.explorewithme.model.request.Request;
import ru.practicum.explorewithme.model.request.RequestMapper;
import ru.practicum.explorewithme.model.request.Status;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.RequestRepository;
import ru.practicum.explorewithme.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class RequestAuthorizedServiceImpl implements RequestAuthorizedService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    @Override
    public List<ParticipantRequestDto> getUserRequestsToEvents(long userId) {
        eventChecker(0, userId);
        return requestRepository.findAll().stream().map(request -> {
            if (request.getRequesterId() == userId) return request;
            return null;
        })
                .filter(Objects::nonNull)
                .map(RequestMapper::toParticipantRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantRequestDto postUserRequestsToEvents(long userId, long eventId) {
        eventChecker(eventId, userId);
        Event event = eventRepository.findById((eventId));
        if (event.getInitiatorId() == userId) {
            throw new ForbiddenException("");
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new ForbiddenException("");
        }
        if (event.getParticipantLimit() <= event.getConfirmedRequests() && event.getParticipantLimit() != 0) {
            throw new ForbiddenException("");
        }
        Request request;
        if (event.isRequestModeration()) {
            request = new Request(userId, eventId, LocalDateTime.now(), Status.PENDING);
        } else {
            request = new Request(userId, eventId, LocalDateTime.now(), Status.CONFIRMED);
        }
        requestRepository.save(request);
        return RequestMapper.toParticipantRequestDto(requestRepository.findById(request.getId()).get());
    }

    @Override
    public ParticipantRequestDto cancelUserRequestsToEvents(long userId, long requestId) {
        eventChecker(0, userId);
        Request request = requestRepository.findById(requestId).get();
        request.setStatus(Status.CANCELED);
        requestRepository.save(request);
        return RequestMapper.toParticipantRequestDto(request);
    }

    public void eventChecker(long eventId, long userId) {
        if (!eventRepository.existsById(eventId) && eventId != 0) throw new NotFoundException("");
        if (userRepository.findById(userId).isEmpty()) throw new NotFoundException("");
    }
}
