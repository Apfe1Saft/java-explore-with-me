package ru.practicum.explorewithme.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.request.Request;
import ru.practicum.explorewithme.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "annotation")
    @Size(min = 20, max = 2000)
    private String annotation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false, nullable = false)
    @ToString.Exclude
    private Category category;

    @Column(name = "confirmed_requests")
    private int confirmedRequests;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "description")
    @Size(min = 20, max = 7000)
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Column(name = "initiator", nullable = false)
    private long initiatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator", insertable = false, updatable = false, nullable = false)
    @ToString.Exclude
    private User initiator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    private List<Request> requests;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "lat", column = @Column(name = "locations")),
            @AttributeOverride(name = "lan", column = @Column(name = "locations"))
    })
    private Location location;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private boolean requestModeration;

    @Column(name = "event_state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "title")
    @Size(min = 3, max = 120)
    private String title;

    @Column(name = "views")
    private int views;

    public Event(long id, String annotation, Category category, int confirmedRequests,
                 LocalDateTime now, String description, LocalDateTime eventDate, long userId, boolean paid, String title, int views) {
        this.id = id;
        this.annotation = annotation;
        this.category = category;
        this.confirmedRequests = confirmedRequests;
        this.createdOn = now;
        this.description = description;
        this.eventDate = eventDate;
        this.initiatorId = userId;
        this.paid = paid;
        this.title = title;
        this.views = views;
    }
}
