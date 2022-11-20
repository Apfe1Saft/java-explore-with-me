package ru.practicum.explorewithme.administrator.compilation;

import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;

public interface CompilationAdminService {
    CompilationDto postCompilation(NewCompilationDto compilationDto);

    void deleteCompilation(long compId);

    void deleteEventFromCompilation(long compId, long eventId);

    void patchCompilation(long compId, long eventId);

    void deleteMainCompilation(long compId);

    void patchMainCompilation(long compId);
}
