package ru.practicum.explorewithme.model.request;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private long id;

    @Column(name = "requester_id")
    private long requesterId;

    @Column(name = "event_id")
    private long eventId;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Request(long requesterId, long eventId, LocalDateTime created, Status status) {
        this.eventId = eventId;
        this.requesterId = requesterId;
        this.created = created;
        this.status = status;
    }
}