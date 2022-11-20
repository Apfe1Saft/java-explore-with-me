package ru.practicum.explorewithme.administrator.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.administrator.exception.NotFoundException;
import ru.practicum.explorewithme.repository.CommentRepository;

@Component
@RequiredArgsConstructor
@Getter
public class CommentAdminServiceImpl implements CommentAdminService {
    private final CommentRepository commentRepository;

    @Override
    public void deleteCommentByAdmin(long commentId) {
        if (!commentRepository.existsById(commentId))
            throw new NotFoundException("Comment with id: " + commentId + " is not exist.");
        commentRepository.deleteById(commentId);
    }
}
