package de.frudisch.event;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@MicronautTest
class EventControllerIT {

    @Inject
    @Client("/event")
    HttpClient httpClient;

    @Inject
    ObjectMapper objectMapper;

    @Test
    public void shouldCreateEventsAndSelectTheCreatedEvents() throws IOException {
        Event createdEvent = httpClient.toBlocking().retrieve(HttpRequest.POST("/",
                        objectMapper.writeValueAsString(new Event(UUID.randomUUID(),
                                LocalDate.of(2023, 1, 26),
                                LocalTime.of(18, 0),
                                LocalTime.of(20, 0),
                                null))),
                Event.class);

        List<Event> eventListResponse =
                httpClient.toBlocking().retrieve(HttpRequest.GET("/"),  Argument.of(List.class, Event.class));

        Assertions.assertThat(eventListResponse).contains(createdEvent);
    }

}