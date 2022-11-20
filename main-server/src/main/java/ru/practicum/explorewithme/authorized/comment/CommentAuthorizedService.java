package ru.practicum.explorewithme.authorized.comment;

import ru.practicum.explorewithme.model.comment.Comment;
import ru.practicum.explorewithme.model.comment.CommentDto;
import ru.practicum.explorewithme.model.comment.PatchedComment;

public interface CommentAuthorizedService {
    CommentDto postCommentByUser(CommentDto commentId, long userId);

    CommentDto patchCommentByUser(long eventDto, long userId, PatchedComment patchedComment);

    void deleteCommentByUser(long commentId, long userId);
}
