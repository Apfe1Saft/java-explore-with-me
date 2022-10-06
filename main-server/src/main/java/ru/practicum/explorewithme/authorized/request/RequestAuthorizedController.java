package ru.practicum.explorewithme.authorized.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.event.EventShortDto;
import ru.practicum.explorewithme.model.request.ParticipantRequestDto;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class RequestAuthorizedController {
    private  final RequestAuthorizedService service;

    @GetMapping("/{userId}/requests")
    public List<ParticipantRequestDto> getUserRequestsToEvents(@PathVariable("userId") long userId) {
        return service.getUserRequestsToEvents(userId);
    }

    @PostMapping("/{userId}/requests")
    public ParticipantRequestDto  postUserRequestsToEvents(@PathVariable("userId") long userId, @RequestParam long eventId) {
        return service.postUserRequestsToEvents(userId,eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipantRequestDto cancelUserRequestsToEvents(@PathVariable("userId") long userId, @PathVariable("requestId") long requestId) {
        return service.cancelUserRequestsToEvents(userId,requestId);
    }

}
