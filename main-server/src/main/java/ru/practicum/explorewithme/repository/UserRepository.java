package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
