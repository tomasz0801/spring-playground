package com.example.playground;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping(value = "people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final Map<String, Person> cache = prepareCache();

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        return new ResponseEntity<>(cache.values().stream().toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Person person) {
        cache.putIfAbsent(person.id(), person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private static Map<String, Person> prepareCache() {
        Map<String, Person> cache = new ConcurrentHashMap<>();
        String uuid1 = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        cache.put(uuid1, new Person(uuid1, "Eric", "Cartman", 10));
        cache.put(uuid2, new Person(uuid2, "Stan", "Marsh", 11));
        return cache;
    }

}