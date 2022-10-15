package ru.practicum.explorewithme.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explorewithme.model.Pattern;
import ru.practicum.explorewithme.model.category.CategoryDto;
import ru.practicum.explorewithme.model.user.UserShortDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFullOutDto {
    private long id;
    private String annotation;
    private String description;

    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime createdOn;

    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime eventDate;

    private Location location;
    private CategoryDto category;
    private UserShortDto initiator;
    private boolean paid;
    private Integer participantLimit;

    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime publishedOn;

    private boolean requestModeration;
    private State state;
    private String title;
}
