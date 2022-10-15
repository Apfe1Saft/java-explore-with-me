package ru.practicum.explorewithme.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explorewithme.model.Pattern;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdateEventRequest {
    @Length(min = 20, max = 2000)
    private String annotation;
    private Long category;
    @Length(min = 20, max = 7000)
    private String description;
    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime eventDate;
    private Long eventId;
    private boolean paid;
    private Integer participantLimit;
    @Length(min = 3, max = 120)
    private String title;
}