package com.ticketmaster.eventmanagement.service;

import com.ticketmaster.eventmanagement.EventsInfoClient;
import com.ticketmaster.eventmanagement.exception.UnMatchedException;
import com.ticketmaster.eventmanagement.model.Artist;
import com.ticketmaster.eventmanagement.model.Event;
import com.ticketmaster.eventmanagement.model.Venue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class EventServiceTest {
    @InjectMocks
    EventService eventService;

    @Mock
    EventsInfoClient infoClient;

    @Test
    public void retrieveArtistUsingId() {
        Mockito.when(infoClient.retrieveListOfArtist()).thenReturn(Collections.singletonList(getArtist()));
        Mockito.when(infoClient.retrieveListOfEvents()).thenReturn(Collections.singletonList(getEvent()));
        Mockito.when(infoClient.retrieveListOfVenue()).thenReturn(Collections.singletonList(getVenue()));
        Artist artist = eventService.retrieveArtistInfo("12");
        Assertions.assertEquals("Trial Artist", artist.getName());
        Assertions.assertEquals("31", artist.getEvents().get(0).getId());
        Assertions.assertEquals("Event title", artist.getEvents().get(0).getTitle());
        Assertions.assertEquals("4", artist.getEvents().get(0).getVenue().getId());
        Assertions.assertEquals("Venue name", artist.getEvents().get(0).getVenue().getName());
        Assertions.assertEquals("Mumbai", artist.getEvents().get(0).getVenue().getCity());
    }

    @Test
    public void artistIdDoesNotExists() {

        Assertions.assertThrowsExactly(UnMatchedException.class, () -> eventService.retrieveArtistInfo("14"));
    }

    private Artist getArtist() {
        Artist artist = new Artist();
        artist.setId("12");
        artist.setName("Trial Artist");
        return artist;
    }

    private Event getEvent() {
        Event event = new Event();
        event.setId("31");
        Artist artist = new Artist();
        artist.setId("12");
        event.setArtists(Collections.singletonList(artist));
        event.setTitle("Event title");
        Venue venue = new Venue();
        venue.setId("4");
        event.setVenue(venue);
        return event;
    }

    private Venue getVenue() {
        Venue venue = new Venue();
        venue.setId("4");
        venue.setName("Venue name");
        venue.setCity("Mumbai");
        return venue;
    }
}
