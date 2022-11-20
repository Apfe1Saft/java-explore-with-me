package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.event.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(long i);

    List<Event> findAllByInitiatorId(long i);
}
