package ru.practicum.explorewithme.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Column(name = "lat", table = "locations")
    private double lat;
    @Column(name = "lon", table = "locations")
    private double lon;

}
