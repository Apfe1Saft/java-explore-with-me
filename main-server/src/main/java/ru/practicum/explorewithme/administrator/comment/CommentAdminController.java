package ru.practicum.explorewithme.administrator.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentAdminController {
    private final CommentAdminService service;

    @DeleteMapping("/{commentId}")
    public void deleteCommentByUser(@PathVariable("commentId") long commentId) {
        log.debug("Opened: DELETE /admin/comments/{commentId} request with commentId: " + commentId);
        service.deleteCommentByAdmin(commentId);
    }
}
