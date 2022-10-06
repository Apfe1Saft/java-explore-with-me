package ru.practicum.explorewithme.model.event;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@ToString
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private double lat;
    private double lon;
}
