package ru.practicum.explorewithme.model.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CommentDto {
    private long id;
    private long eventId;
    private String authorName;
    private String text;
    private LocalDate created = LocalDate.now();

    public CommentDto(long id, String text, LocalDate created, long eventId, String name) {
        this.id = id;
        this.text = text;
        this.created = created;
        this.authorName = name;
        this.eventId = eventId;
    }

    public CommentDto(long id, String text, long eventId, String name) {
        this.id = id;
        this.text = text;
        this.authorName = name;
        this.eventId = eventId;
    }
}
