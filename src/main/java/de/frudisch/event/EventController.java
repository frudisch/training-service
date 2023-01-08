package de.frudisch.event;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;

@Controller
public record EventController(EventService eventService) {

    @Get("event")
    public HttpResponse<List<Event>> selectEvents() {
        List<Event> eventList = eventService.selectEvents();
        return HttpResponse.ok(eventList);
    }

    @Post("event")
    public HttpResponse<Event> createEvents(@Body Event event) {
        Event persistedEvent = eventService.createEvent(event);
        return HttpResponse.created(persistedEvent);
    }
}
