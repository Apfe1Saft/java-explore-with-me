package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.request.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
