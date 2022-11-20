package ru.practicum.explorewithme.model.event;

import ru.practicum.explorewithme.model.category.CategoryMapper;
import ru.practicum.explorewithme.model.user.UserMapper;

import java.time.LocalDateTime;

public class EventMapper {
    static public NewEventDto toNewEventDto(Event event) {
        return new NewEventDto(
                event.getId(), event.getAnnotation(), event.getDescription(),
                event.getCategory().getId(), event.getConfirmedRequests()
                , event.getEventDate()
                , event.isPaid(), event.getTitle(), event.getViews()
        );
    }

    static public EventFullOutDto toEventFullOutDto(Event event) {
        return new EventFullOutDto(
                event.getId(),
                event.getAnnotation(),
                event.getDescription(),
                event.getCreatedOn(),
                event.getEventDate(),
                event.getLocation(),
                CategoryMapper.toCategoryDto(event.getCategory()),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.isPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.isRequestModeration(),
                event.getState(),
                event.getTitle()
        );
    }

    static public EventFullDto toEventFullDto(Event event) {
        return new EventFullDto(
                event.getId(),
                event.getAnnotation(),
                event.getDescription(),
                event.getCreatedOn(),
                event.getEventDate(),
                event.getLocation(),
                event.getConfirmedRequests(),
                CategoryMapper.toCategoryDto(event.getCategory()),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.isPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.isRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    static public EventShortDto toEventShortDto(Event event) {
        EventShortDto shortDto = new EventShortDto();
        shortDto.setId(event.getId());
        shortDto.setTitle(event.getTitle());
        shortDto.setAnnotation(event.getAnnotation());
        shortDto.setCategory(CategoryMapper.toCategoryDto(event.getCategory()));
        shortDto.setPaid(event.isPaid());
        shortDto.setEventDate(event.getEventDate());
        shortDto.setInitiator(UserMapper.toUserShortDto(event.getInitiator()));
        shortDto.setViews(event.getViews());
        shortDto.setConfirmedRequests(event.getConfirmedRequests());
        return shortDto;
    }

    static public Event toEvent(NewEventDto eventDto, long userId) {
        return new Event(
                eventDto.getId(),
                eventDto.getAnnotation(),
                eventDto.getCategory(),
                eventDto.getConfirmedRequests(),
                LocalDateTime.now(),
                eventDto.getDescription(),
                eventDto.getEventDate(),
                userId,
                eventDto.isPaid(),
                eventDto.getTitle(),
                eventDto.getViews()
        );
    }

    static public UpdateEventRequest toUpdateEventRequest(Event event) {
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        updateEventRequest.setAnnotation(event.getAnnotation());
        updateEventRequest.setCategory(event.getCategoryId());
        updateEventRequest.setDescription(event.getDescription());
        updateEventRequest.setEventDate(event.getEventDate());
        updateEventRequest.setEventId(event.getId());
        updateEventRequest.setPaid(event.isPaid());
        updateEventRequest.setParticipantLimit(event.getParticipantLimit());
        updateEventRequest.setTitle(event.getTitle());
        return updateEventRequest;
    }

    static public EventOutDto toEventOutDto(Event event) {
        EventOutDto update = new EventOutDto();
        update.setAnnotation(event.getAnnotation());
        update.setCategory(event.getCategory());
        update.setDescription(event.getDescription());
        update.setEventDate(event.getEventDate());
        update.setId(event.getId());
        update.setPaid(event.isPaid());
        update.setParticipantLimit(event.getParticipantLimit());
        update.setTitle(event.getTitle());
        update.setInitiator(event.getInitiatorId());
        update.setRequestModeration(event.isRequestModeration());
        update.setLocation(event.getLocation());
        update.setCreatedOn(event.getCreatedOn());
        update.setState(event.getState());
        return update;
    }
}
