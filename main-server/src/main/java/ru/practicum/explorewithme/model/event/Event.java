package ru.practicum.explorewithme.model.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.practicum.explorewithme.model.Pattern;
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
@SecondaryTable(name = "locations", pkJoinColumns = @PrimaryKeyJoinColumn(name = "event_id"))
@Getter
@Setter
@ToString
public class Event {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "title")
    @Size(min = 3, max = 120)
    private String title;

    @Column(name = "annotation")
    @Size(min = 20, max = 2000)
    private String annotation;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false, nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ToString.Exclude
    private Category category;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "event_date")
    @JsonFormat(pattern = Pattern.TIME_PATTERN)
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator", insertable = false, updatable = false, nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    //@JsonBackReference
    @ToString.Exclude
    private User initiator;

    @Column(name = "views")
    private int views;

    @Column(name = "confirmed_requests")
    private int confirmedRequests;

    @Column(name = "description")
    @Size(min = 20, max = 7000)
    private String description;

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "event_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Embedded
    private Location location;

    @Column(name = "request_moderation")
    private boolean requestModeration = true;

    @Column(name = "initiator", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private long initiatorId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @ToString.Exclude
    @JsonBackReference
    private List<Request> requests;

    @Column(name = "category_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private long categoryId;

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

    public Event(long id, String annotation, long categoryId, int confirmedRequests,
                 LocalDateTime now, String description, LocalDateTime eventDate, long userId, boolean paid, String title, int views) {
        this.id = id;
        this.annotation = annotation;
        this.categoryId = categoryId;
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
