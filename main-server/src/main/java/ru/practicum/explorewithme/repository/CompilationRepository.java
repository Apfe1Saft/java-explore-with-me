package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explorewithme.model.compilation.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    @Modifying
    @Query(value = "update Compilation compilation set compilation.pinned = ?1 where compilation.id = ?2")
    void setMainCompilation(boolean pinned, long compId);
}
