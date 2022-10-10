package ru.practicum.explorewithme.administrator.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.AdminUpdateEventDto;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.event.Event;

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
                                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime rangeStart,
                                     @RequestParam(required = false)
                                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime rangeEnd,
                                     @RequestParam(defaultValue =  "0") int from,
                                     @RequestParam(defaultValue = "10") int size) {
        return service.findEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public Event putEvent(@PathVariable("eventId") long eventId,@RequestBody AdminUpdateEventDto eventDto) {
        return service.putEvent(eventId,eventDto);
    }

    @PatchMapping("/{eventId}/publish")
    public Event publishEvent(@PathVariable("eventId") long eventId) {
        Event event = (service.publishEvent(eventId,true));
        event = new Event(service.publishEvent(eventId,true));
        return event;
    }

    @PatchMapping("/{eventId}/reject")
    public Event rejectEvent(@PathVariable("eventId") long eventId) {
        return  service.publishEvent(eventId,false);
    }
}
