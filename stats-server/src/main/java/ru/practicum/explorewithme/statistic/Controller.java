package ru.practicum.explorewithme.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final StatisticServiceImpl statisticService;

    @PostMapping("/hit")
    public void saveResponse(@RequestBody EndpointHit endpointHit) {
        statisticService.saveResponse(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                    @RequestParam String[] uris,
                                    @RequestParam boolean unique) {
        return statisticService.getStats(start, end, uris, unique);
    }
}