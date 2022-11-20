package ru.practicum.explorewithme.authorized.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.ForbiddenException;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;
import ru.practicum.explorewithme.model.request.Request;
import ru.practicum.explorewithme.model.request.RequestMapper;
import ru.practicum.explorewithme.model.request.Status;
import ru.practicum.explorewithme.repository.CategoryRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.RequestRepository;
import ru.practicum.explorewithme.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class EventAuthorizedServiceImpl implements EventAuthorizedService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<EventShortDto> getEventsByUser(long userId, int from, int size) {
        eventChecker(0, userId);
        List<Event> events = eventRepository.findAllByInitiatorId(userId);
        Pageable pageable = PageRequest.of(from, size, Sort.by("id").descending());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), events.size());
        events = new PageImpl<>(events.subList(start, end), pageable, events.size()).getContent();
        return events.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    @Override
    public Event patchEventsByUser(UpdateEventRequest eventDto, long userId) {
        eventChecker(0, userId);
        Event event = eventRepository.findById(eventDto.getEventId()).get();
        event.setAnnotation(eventDto.getAnnotation());
        event.setCategoryId(event.getCategoryId());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setPaid(eventDto.isPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setTitle(eventDto.getTitle());
        eventRepository.save(event);
        return eventRepository.findById(event.getId());
    }

    @Override
    public Event postEventsByUser(NewEventDto eventDto, long userId) {
        eventChecker(0, userId);
        Event event = EventMapper.toEvent(eventDto, userId);
        event.setInitiator(userRepository.findById(userId).get());
        event.setCategory(categoryRepository.findById(event.getCategoryId()).get());
        event.setState(State.PENDING);
        event.setLocation(eventDto.getLocation());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.isRequestModeration());
        eventRepository.save(event);
        return event;
    }

    @Override
    public Event getEventByUser(long userId, long eventId) {
        eventChecker(eventId, userId);
        return eventRepository.findById(eventId);
    }

    @Override
    public Event patchEventByUser(long userId, long eventId) {
        eventChecker(eventId, userId);
        Event event = eventRepository.findById(eventId);
        event.setState(State.CANCELED);
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<ParticipantRequestDto> getRequestsByUser(long userId, long eventId) {
        eventChecker(eventId, userId);
        return eventRepository.findById(eventId).getRequests()
                .stream().map(RequestMapper::toParticipantRequestDto).collect(Collectors.toList());
    }

    @Override
    public ParticipantRequestDto confirmRequestsByUser(long userId, long eventId, long reqId) {
        eventChecker(eventId, userId);
        List<Request> requests = eventRepository.findById(eventId).getRequests();
        Request request = null;
        for (Request req : requests) {
            if (req.getId() == reqId) {
                request = req;
                break;
            }
        }

        if (request == null) throw new NotFoundException("");
        if (!request.getStatus().equals(Status.PENDING)) throw new ForbiddenException("");
        request.setStatus(Status.CONFIRMED);
        requestRepository.save(request);
        return RequestMapper.toParticipantRequestDto(request);
    }

    @Override
    public ParticipantRequestDto rejectRequestsByUser(long userId, long eventId, long reqId) {
        eventChecker(eventId, userId);
        List<Request> requests = eventRepository.findById(eventId).getRequests();
        Request request = null;
        for (Request req : requests) {
            if (req.getId() == reqId) {
                request = req;
                break;
            }
        }
        if (request == null) throw new NotFoundException("");
        if (!request.getStatus().equals(Status.PENDING)) throw new ForbiddenException("");
        request.setStatus(Status.REJECTED);
        requestRepository.save(request);
        return RequestMapper.toParticipantRequestDto(request);
    }

    public void eventChecker(long eventId, long userId) {
        if (eventId == 0) {
            if (userRepository.findById(userId).isPresent()) {
                return;
            }
            throw new ForbiddenException("");
        }
        if (eventRepository.existsById(eventId)) {
            if (eventRepository.findById(eventId).getInitiator().getId() == userId) {
                return;
            }
            throw new ForbiddenException("");
        }
        throw new ForbiddenException("");
    }

}
