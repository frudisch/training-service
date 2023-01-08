package de.frudisch.event;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public record EventService (EventRepository eventRepository) {
    public List<Event> selectEvents() {
        Iterable<Event> events = eventRepository.findAll();
        return StreamSupport.stream(events.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
