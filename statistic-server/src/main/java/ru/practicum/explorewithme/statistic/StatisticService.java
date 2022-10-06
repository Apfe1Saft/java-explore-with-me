package ru.practicum.explorewithme.statistic;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {

    void saveResponse(EndpointHit endpointHit);

    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, String[] uri, boolean unique);
}
