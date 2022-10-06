package ru.practicum.explorewithme.model.event;

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

    private Category category;

    private int confirmedRequests;

    private LocalDateTime eventDate;

    private User initiator;

    private boolean paid;

    @Length(min = 3, max = 120)
    private String title;

    private int views;
}
