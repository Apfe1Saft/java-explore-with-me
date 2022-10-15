package ru.practicum.explorewithme.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explorewithme.model.Pattern;
import ru.practicum.explorewithme.model.category.Category;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventOutDto {
    private Long id;
    @Length(min = 3, max = 120)
    private String title;
    @Length(min = 20, max = 2000)
    private String annotation;
    private Category category;
    private boolean paid;
    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime eventDate;
    private long initiator;
    @Length(min = 20, max = 7000)
    private String description;
    private Integer participantLimit;
    private State state;
    private LocalDateTime createdOn;
    private Location location;
    private boolean requestModeration;
}