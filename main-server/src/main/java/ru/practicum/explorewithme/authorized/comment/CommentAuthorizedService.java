package ru.practicum.explorewithme.authorized.comment;

import ru.practicum.explorewithme.model.comment.CommentDto;
import ru.practicum.explorewithme.model.comment.PatchedComment;

public interface CommentAuthorizedService {
    CommentDto patchCommentByUser(CommentDto commentId, long userId);

    CommentDto postCommentByUser(long eventDto, long userId, PatchedComment patchedComment);

    void deleteCommentByUser(long commentId, long userId);
}
