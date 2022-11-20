package ru.practicum.explorewithme.authorized.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.*;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class EventAuthorizedController {
    private final EventAuthorizedService service;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> getEventsByUser(@PathVariable("userId") int userId,
                                               @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size) {
        log.debug("Opened: GET /users/{userId}/events request with id: {}, from: {}, size: {}", userId, from, size);
        return service.getEventsByUser(userId, from, size);
    }

    @PatchMapping("/{userId}/events")
    public EventOutDto patchEventsByUser(@RequestBody UpdateEventRequest eventDto, @PathVariable("userId") long userId) {
        log.debug("Opened: PATCH /users/{userId}/events request with userId: " + userId);
        return EventMapper.toEventOutDto(service.patchEventsByUser(eventDto, userId));
    }

    @PostMapping("/{userId}/events")
    public EventFullDto postEventsByUser(@RequestBody NewEventDto eventDto, @PathVariable("userId") long userId) {
        log.debug("Opened: POST /users/{userId}/events request with userId: " + userId);
        return EventMapper.toEventFullDto(service.postEventsByUser(eventDto, userId));
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getEventByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        log.debug("Opened: GET /users/{userId}/events/{eventId} request with userId: {}, eventId: {}", userId, eventId);
        return EventMapper.toEventFullDto(service.getEventByUser(userId, eventId));
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventOutDto patchEventByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        log.debug("Opened: PATCH /users/{userId}/events/{eventId} request with userId: {}, eventId: {}", userId, eventId);
        return EventMapper.toEventOutDto(service.patchEventByUser(userId, eventId));
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipantRequestDto> getRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        log.debug("Opened: GET /users/{userId}/events/{eventId}/requests request with userId: {}, eventId: {}", userId, eventId);
        return service.getRequestsByUser(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipantRequestDto confirmRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId,
                                                       @PathVariable("reqId") long reqId) {
        log.debug("Opened: PATCH /users/{userId}/events/{eventId}/requests/{reqId}/confirm request with userId: {}, eventId: {}, reqId: {}", userId, eventId, reqId);
        return service.confirmRequestsByUser(userId, eventId, reqId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipantRequestDto rejectRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId,
                                                      @PathVariable("reqId") long reqId) {
        log.debug("Opened: PATCH /users/{userId}/events/{eventId}/requests/{reqId}/reject request with userId: {}, eventId: {}, reqId: {}", userId, eventId, reqId);
        return service.rejectRequestsByUser(userId, eventId, reqId);
    }
}