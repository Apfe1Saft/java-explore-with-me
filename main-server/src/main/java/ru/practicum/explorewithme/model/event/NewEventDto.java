package ru.practicum.explorewithme.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.user.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    private long id;

    @Length(min = 20, max = 2000)
    private String annotation;

    @Length(min = 20, max = 7000)
    private String description;

    private Long category;

    private int confirmedRequests;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private Location location;

    private int participantLimit;

    private boolean paid;

    @Length(min = 3, max = 120)
    private String title;

    private int views;
    private boolean requestModeration;

    public NewEventDto(long id, String annotation, String description, long categoryId, int confirmedRequests, LocalDateTime eventDate, boolean paid, String title, int views) {
        this.id = id;
        this.annotation = annotation;
        this.description = description;
        this.category = categoryId;
        this.confirmedRequests = confirmedRequests;
        this.eventDate =eventDate;
        this.paid = paid;
        this.views = views;
        this.title = title;
    }
}
