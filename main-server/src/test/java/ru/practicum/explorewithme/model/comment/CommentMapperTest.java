package ru.practicum.explorewithme.model.comment;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.user.User;

import java.time.LocalDate;

class CommentMapperTest {

    @Test
    void toComment() {
        CommentDto commentDto = new CommentDto(1, "text", LocalDate.now(), 1, "Karl");
        User user = new User();
        user.setId(1);
        Event event = new Event();
        event.setId(1);
        Assertions.assertEquals(CommentMapper.toComment(commentDto, event, user).getId(), 1);
    }

    @Test
    void toCommentDto() {
        CommentDto commentDto = new CommentDto(1, "text", LocalDate.now(), 1, "Karl");
        User user = new User();
        user.setId(1);
        Event event = new Event();
        event.setId(1);
        Comment comment = new Comment(2, "text", event, user, LocalDate.now());
        Assertions.assertEquals(CommentMapper.toCommentDto(comment).getId(), 2);
    }
}