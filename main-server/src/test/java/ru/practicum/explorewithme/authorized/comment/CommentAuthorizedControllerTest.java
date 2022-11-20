package ru.practicum.explorewithme.authorized.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.explorewithme.model.comment.CommentDto;
import ru.practicum.explorewithme.model.comment.PatchedComment;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentAuthorizedController.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"db.name=comment_test"})
@AutoConfigureMockMvc
class CommentAuthorizedControllerTest {

    @MockBean
    private CommentAuthorizedService service;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void patchComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorName("Author");
        commentDto.setEventId(1);
        commentDto.setText("This event is cool.");

        when(service.patchCommentByUser(any(), anyLong())).thenReturn(commentDto);

        mockMvc.perform(patch("/users/1/comments")
                .content(mapper.writeValueAsString(commentDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0L), Long.class));
        verify(service, times(1)).patchCommentByUser(any(), anyLong());
    }

    @Test
    void postComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorName("Author");
        commentDto.setEventId(1);
        commentDto.setId(1);
        commentDto.setText("This event is cool.");

        PatchedComment patchedComment = new PatchedComment();
        when(service.postCommentByUser(anyLong(), anyLong(), any())).thenReturn(commentDto);

        mockMvc.perform(post("/users/1/comments/1")
                .content(mapper.writeValueAsString(patchedComment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L), Long.class));
        verify(service, times(1)).postCommentByUser(anyLong(), anyLong(), any());
    }
}