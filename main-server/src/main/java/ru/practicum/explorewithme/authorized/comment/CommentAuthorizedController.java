package ru.practicum.explorewithme.authorized.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.model.comment.CommentDto;
import ru.practicum.explorewithme.model.comment.PatchedComment;

@RestController
@RequestMapping(path = "/users")
@Slf4j
@RequiredArgsConstructor
public class CommentAuthorizedController {
    private final CommentAuthorizedService service;
    // Создание комментария
    @PostMapping("/{userId}/comments")
    public CommentDto postComment(@RequestBody CommentDto commentDto, @PathVariable("userId") long userId) {
        log.debug("Opened: POST /users/{userId}/comments request with userId: "+userId);
        return service.postCommentByUser(commentDto,userId);
    }

    // Редактирование комментария
    @PatchMapping("/{userId}/comments/{eventId}")
    public CommentDto patchComment(@PathVariable("userId") long userId,@PathVariable("eventId") long eventId,@RequestBody PatchedComment patchedComment) {
        log.debug("Opened: PATCH /users/{userId}/comments/{commentId} request with userId: "+userId);
        return service.patchCommentByUser(eventId,userId,patchedComment);
    }

    // Удаление комментария
    @DeleteMapping("/{userId}/comments/{commentId}")
    public void deleteCommentByUser(@PathVariable("userId") long userId,@PathVariable("commentId") long commentId) {
        log.debug("Opened: DELETE /users/{userId}/comments/{commentId} request with userId: "+userId);
         service.deleteCommentByUser(commentId,userId);
    }
}
