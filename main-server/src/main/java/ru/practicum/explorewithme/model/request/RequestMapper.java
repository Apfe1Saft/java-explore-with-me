package ru.practicum.explorewithme.model.request;

public class RequestMapper {
    public static ParticipantRequestDto toParticipantRequestDto(Request request) {
        return new ParticipantRequestDto(
                request.getId(),
                request.getEventId(),
                request.getCreated(),
                request.getRequesterId(),
                request.getStatus()
        );
    }
}
