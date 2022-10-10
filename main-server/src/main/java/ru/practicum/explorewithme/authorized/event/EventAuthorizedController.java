package ru.practicum.explorewithme.authorized.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.EventFullDto;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.event.NewEventDto;
import ru.practicum.explorewithme.model.event.UpdateEventRequest;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;

import javax.validation.Valid;
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
        System.out.println("GET/{userId}/events");
        System.out.println("userId: "+userId);
        System.out.println(from+" "+size);
        return service.getEventsByUser(userId,from,size);
    }

    @PatchMapping("/{userId}/events")
    public EventFullDto patchEventsByUser(@RequestBody UpdateEventRequest eventDto, @PathVariable("userId") long userId) {
        System.out.println("PATCH/{userId}/events");
        System.out.println(eventDto);
        return service.patchEventsByUser(eventDto,userId);
    }

    @PostMapping("/{userId}/events")
    public EventFullDto  postEventsByUser(@RequestBody NewEventDto eventDto,@PathVariable("userId") long userId) {
        System.out.println("POST/{userId}/events");
        System.out.println(eventDto);
        return service.postEventsByUser(eventDto,userId);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto  getEventByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        System.out.println("GET/{userId}/events/{userID}");
        return service.getEventByUser(userId,eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto patchEventByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        System.out.println("PATCH/{userId}/events/{eventID}");
        return service.patchEventByUser(userId,eventId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipantRequestDto> getRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId) {
        System.out.println("GET/{userId}/events/requests");
        return service.getRequestsByUser(userId,eventId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public ParticipantRequestDto  confirmRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId,
                                                     @PathVariable("reqId") long reqId) {
        return service.confirmRequestsByUser(userId,eventId,reqId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public ParticipantRequestDto  rejectRequestsByUser(@PathVariable("userId") long userId, @PathVariable("eventId") long eventId,
                                                    @PathVariable("reqId") long reqId) {
        return service.rejectRequestsByUser(userId,eventId,reqId);
    }
}