package ru.pasvitas.teaching.lessontwo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Jokes2.0")
@Table(name = "Jokes2.0")

public class Joke{
    @Id
    @Column(name = "id_joke")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text_joke")
    private String textJoke;

    @CreationTimestamp
    @Column(name = "date_add_joke")
    private LocalDateTime dateAddJoke;

    @UpdateTimestamp
    @Column(name = "date_changes_joke")
    private LocalDateTime dateChangesJoke;
}
