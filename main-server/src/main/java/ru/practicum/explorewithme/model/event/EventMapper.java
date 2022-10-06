package ru.practicum.explorewithme.model.event;

import ru.practicum.explorewithme.model.category.CategoryMapper;
import ru.practicum.explorewithme.model.user.UserMapper;

import java.time.LocalDateTime;

public class EventMapper {
    static public NewEventDto toNewEventDto(Event event) {
        return new NewEventDto(
                event.getId(), event.getAnnotation(), event.getDescription(),
                event.getCategory(), event.getConfirmedRequests()
                , event.getEventDate(), event.getInitiator()
                , event.isPaid(), event.getTitle(), event.getViews()
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
    static public EventShortDto toEventShortDto(Event event){
        return new EventShortDto(
                event.getId(),
                event.getAnnotation(),
                CategoryMapper.toCategoryDto(event.getCategory()),
                event.getConfirmedRequests(),
                event.getEventDate(),
                UserMapper.toUserShortDto(event.getInitiator()),
                event.isPaid(),
                event.getTitle(),
                event.getViews()
        );
    }
    static public Event toEvent(NewEventDto eventDto,long userId){
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
}
