package ru.practicum.explorewithme.administrator.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.Pattern;
import ru.practicum.explorewithme.model.event.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@Slf4j
@RequiredArgsConstructor
public class EventAdminController {
    private final EventAdminService service;

    @GetMapping
    public List<Event> findEvents(@RequestParam(required = false) long[] users,
                                  @RequestParam(required = false) State[] states,
                                  @RequestParam(required = false) long[] categories,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(pattern = Pattern.TIME_PATTERN) LocalDateTime rangeStart,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(pattern = Pattern.TIME_PATTERN) LocalDateTime rangeEnd,
                                  @RequestParam(defaultValue = "0") int from,
                                  @RequestParam(defaultValue = "10") int size) {
        log.debug("Admin: GET /admin/events request with users: {}, states: {}, categories: {}, rangeStart: {}," +
                        " rangeEnd: {}, from: {}, size: {}"
                , users, states, categories, rangeStart, rangeEnd, from, size);

        return service.findEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public Event putEvent(@PathVariable("eventId") long eventId, @RequestBody AdminUpdateEventDto eventDto) {
        log.debug("Admin: PUT /admin/events/{eventId} request with eventId: " + eventId);
        return service.putEvent(eventId, eventDto);
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullOutDto publishEvent(@PathVariable("eventId") long eventId) {
        log.debug("Admin: PATCH /admin/events/{eventId}/publish request with eventId: " + eventId);
        Event event = (service.publishEvent(eventId, true));
        return EventMapper.toEventFullOutDto(event);
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullOutDto rejectEvent(@PathVariable("eventId") long eventId) {
        log.debug("Admin: PATCH /admin/events/{eventId}/reject request with eventId: " + eventId);
        return EventMapper.toEventFullOutDto(service.publishEvent(eventId, false));
    }
}
