package ru.practicum.explorewithme.administrator.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
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
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime rangeStart,
                                     @RequestParam(required = false)
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime rangeEnd,
                                     @RequestParam(defaultValue =  "0") int from,
                                     @RequestParam(defaultValue = "10") int size) {
        return service.findEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public Event putEvent(@PathVariable("userId") long userId,@RequestBody final Event event) {
        return service.putEvent(userId,event);
    }

    @PostMapping("/{eventId}/publish")
    public Event publishEvent(@PathVariable("userId") long userId) {
        return service.publishEvent(userId,true);
    }

    @PostMapping("/{eventId}/reject")
    public Event rejectEvent(@PathVariable("userId") long userId) {
        return  service.publishEvent(userId,false);
    }
}
