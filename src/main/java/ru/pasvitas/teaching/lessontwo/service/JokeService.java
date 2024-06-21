package ru.pasvitas.teaching.lessontwo.service;

import java.util.List;
import java.util.Optional;

import ru.pasvitas.teaching.lessontwo.model.Joke;

//Интерфейс для сервиса
public interface JokeService {

    void addJoke(Joke joke);

    List<Joke> getAllJokes();

    Optional<Joke> getJokeById(Long id);

    Optional<Joke> putJokeById(Long id, Joke updatedJoke);

    void deleteJokeById(Long id);


}
