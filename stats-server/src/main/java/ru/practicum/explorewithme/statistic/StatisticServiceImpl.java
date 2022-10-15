package ru.practicum.explorewithme.statistic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Getter
public class StatisticServiceImpl implements StatisticService {


    private final Repository repository;

    @Override
    public void saveResponse(EndpointHit endpointHit) {
        repository.save(endpointHit);
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, String[] uri, boolean unique) {
        List<ViewStats> viewStats = unique ? repository.findAllUnique(start, end) : repository.findAllNotUnique(start, end);
        return uri == null ?
                viewStats :
                viewStats.stream()
                        .map(view -> {
                            for (String u : uri) {
                                if (view.getUri().equals(u)) {
                                    return view;
                                }
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
    }
}
