package de.frudisch.event;

import io.micronaut.http.HttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController sut;

    @Test
    public void shouldSelectEventsFromService() {
        List<Event> expected = List.of(
            new Event(UUID.randomUUID(),
                    LocalDate.of(2023, 1, 26),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    null),
            new Event(UUID.randomUUID(),
                    LocalDate.of(2023, 1, 26),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    "Füchse Berlin"));
        when(eventService.selectEvents()).thenReturn(expected);

        HttpResponse<List<Event>> result = sut.selectEvents();

        assertThat(result.code()).isEqualTo(200);
        Assertions.assertThat(result.body()).isEqualTo(expected);
    }
}