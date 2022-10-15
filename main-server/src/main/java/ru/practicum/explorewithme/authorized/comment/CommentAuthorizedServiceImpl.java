package ru.practicum.explorewithme.authorized.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.ForbiddenException;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.model.comment.Comment;
import ru.practicum.explorewithme.model.comment.CommentDto;
import ru.practicum.explorewithme.model.comment.CommentMapper;
import ru.practicum.explorewithme.model.comment.PatchedComment;
import ru.practicum.explorewithme.repository.CommentRepository;
import ru.practicum.explorewithme.repository.EventRepository;
import ru.practicum.explorewithme.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Getter
public class CommentAuthorizedServiceImpl implements CommentAuthorizedService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentDto patchCommentByUser(CommentDto commentDto, long userId) {
        userChecker(userId);
        eventChecker(commentDto.getEventId());
        Comment comment = CommentMapper.toComment(commentDto, eventRepository.getById(commentDto.getEventId()),
                userRepository.getById(userId));
        commentRepository.save(comment);
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public CommentDto postCommentByUser(long commentId, long userId, PatchedComment patchedComment) {
        userChecker(userId);
        commentChecker(commentId);
        userCommentChecker(userId, commentId);
        Comment comment = commentRepository.getById(commentId);
        comment.setText(patchedComment.getText());
        commentRepository.save(comment);
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public void deleteCommentByUser(long commentId, long userId) {
        userChecker(userId);
        commentChecker(commentId);
        userCommentChecker(userId, commentId);
        commentRepository.deleteById(commentId);
    }


    public void userChecker(long userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new NotFoundException("User with id: " + userId + " is not exist.");
    }

    public void eventChecker(long eventId) {
        if (!eventRepository.existsById(eventId))
            throw new NotFoundException("Event with id: " + eventId + " is not exist.");
    }

    public void commentChecker(long commentId) {
        if (!commentRepository.existsById(commentId))
            throw new NotFoundException("Comment with id: " + commentId + " is not exist.");
    }

    public void userCommentChecker(long userId, long commentId) {
        if (commentRepository.getById(commentId).getAuthor().getId() != userId)
            throw new ForbiddenException("Comment with id: " + commentId + " was not created by User with id: " + userId);
    }
}
