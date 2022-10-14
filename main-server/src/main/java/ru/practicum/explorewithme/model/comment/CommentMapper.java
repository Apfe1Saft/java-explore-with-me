package ru.practicum.explorewithme.model.comment;

import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.user.User;

@Component
public class CommentMapper {

    public static Comment toComment(CommentDto commentDto, Event event, User user) {
        return new Comment(
                commentDto.getId(),
                commentDto.getText(),
                event,
                user,
                commentDto.getCreated()
        );

    }

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getCreated(),
                comment.getEvent().getId(),
                comment.getAuthor().getName()
        );
    }
}