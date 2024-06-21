package ru.pasvitas.teaching.lessontwo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.pasvitas.teaching.lessontwo.model.Joke;


public interface JokeRepository extends JpaRepository<Joke, Long> {

}
