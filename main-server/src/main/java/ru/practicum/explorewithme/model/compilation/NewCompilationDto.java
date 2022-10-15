package ru.practicum.explorewithme.model.compilation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class NewCompilationDto {
    private List<Long> events;
    private boolean pinned;
    //@Length(min = 5, max = 120)
    private String title;

    public NewCompilationDto() {
        events = new ArrayList<>();
        pinned = false;
        title = "";
    }
}
