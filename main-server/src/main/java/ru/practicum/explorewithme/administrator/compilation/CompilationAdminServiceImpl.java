package ru.practicum.explorewithme.administrator.compilation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.CompilationMapper;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.repository.CompilationRepository;
import ru.practicum.explorewithme.repository.EventRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class CompilationAdminServiceImpl implements CompilationAdminService {
    private final CompilationRepository repository;
    private final EventRepository eventAdminRepository;

    @Override
    public CompilationDto postCompilation(NewCompilationDto compilationDto) {
        Compilation compilation = new Compilation();
        compilation.setTitle(compilationDto.getTitle());
        compilation.setPinned(compilationDto.isPinned());
        compilation.setEvents(
                eventAdminRepository.findAll().stream()
                        .map(event -> {
                            if (compilationDto.getEvents().contains(event.getId())) return event;
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
        repository.save(compilation);
        return CompilationMapper.toCompilationDto(compilation);
    }

    @Override
    public void deleteCompilation(long compId) {
        repository.deleteById((long) compId);
    }

    @Override
    public void deleteEventFromCompilation(long compId, long eventId) {
        Compilation compilation = repository.findById(compId).get();
        List<Event> events = compilation.getEvents();
        events.removeIf(event -> event.getId() == eventId);
        compilation.setEvents(events);
        repository.save(compilation);
    }

    @Override
    public void patchCompilation(long compId, long eventId) {
        Compilation compilation = repository.findById(compId).get();
        List<Event> events = compilation.getEvents();
        events.add(eventAdminRepository.findById(eventId));
        compilation.setEvents(events);
        repository.save(compilation);
    }

    @Override
    public void deleteMainCompilation(long compId) {
        repository.setMainCompilation(false, compId);
    }

    @Override
    public void patchMainCompilation(long compId) {
        repository.setMainCompilation(true, compId);
    }
}
