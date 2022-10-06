package ru.practicum.explorewithme.model.compilation;

import ru.practicum.explorewithme.model.event.Event;

import java.util.ArrayList;
import java.util.List;

public class CompilationMapper {
    public static CompilationDto toCompilationDto(Compilation compilation){
        List<Long> eventsIds = new ArrayList<>();
        for(Event event : compilation.getEventCompilation()){
            eventsIds.add(event.getId());
        }
        return new CompilationDto(compilation.getId(),eventsIds,compilation.isPinned(),compilation.getTitle());
    }

    public static NewCompilationDto toNewCompilationDto(Compilation compilation){
        List<Long> eventsIds = new ArrayList<>();
        for(Event event : compilation.getEventCompilation()){
            eventsIds.add(event.getId());
        }
        return new NewCompilationDto(eventsIds,compilation.isPinned(),compilation.getTitle());
    }
}