package ru.practicum.explorewithme.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.practicum.explorewithme.model.event.Event;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Data
public class User {
    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "initiator_id")
    @ToString.Exclude
    private Set<Event> events;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    public User(long id,String name,String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
