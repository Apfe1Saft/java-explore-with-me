package ru.practicum.explorewithme.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface Repository extends JpaRepository<EndpointHit, Long> {
    @Query(value = "select distinct(e.uri) as uri, count(e.app) as hits, e.app as app " +
            "from EndpointHit e where e.timestamp > ?1 and e.timestamp < ?2" +
            " group by e.app, (e.uri)")
    List<ViewStats> findAllNotUnique(LocalDateTime start, LocalDateTime end);

    @Query(value = "select distinct(e.uri) as uri, count(e.app) as hits, e.app as app " +
            "from EndpointHit e where e.timestamp > ?1 and e.timestamp < ?2" +
            " group by e.app, (e.uri), e.ip")
    List<ViewStats> findAllUnique(LocalDateTime start, LocalDateTime end);
}