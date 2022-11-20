package ru.practicum.explorewithme.model.compilation;

import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.EventMapper;
import ru.practicum.explorewithme.model.event.EventShortDto;

import java.util.ArrayList;
import java.util.List;

public class CompilationMapper {
    public static CompilationDto toCompilationDto(Compilation compilation) {
        List<EventShortDto> eventsIds = new ArrayList<>();
        for (Event event : compilation.getEvents()) {
            eventsIds.add(EventMapper.toEventShortDto(event));
        }
        return new CompilationDto(compilation.getId(), eventsIds, compilation.isPinned(), compilation.getTitle());
    }

    public static NewCompilationDto toNewCompilationDto(Compilation compilation) {
        List<Long> eventsIds = new ArrayList<>();
        for (Event event : compilation.getEvents()) {
            eventsIds.add(event.getId());
        }
        return new NewCompilationDto(eventsIds, compilation.isPinned(), compilation.getTitle());
    }
}