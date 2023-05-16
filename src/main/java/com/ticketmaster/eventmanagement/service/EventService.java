package com.ticketmaster.eventmanagement.service;

import com.ticketmaster.eventmanagement.EventsInfoClient;
import com.ticketmaster.eventmanagement.exception.UnMatchedException;
import com.ticketmaster.eventmanagement.model.Artist;
import com.ticketmaster.eventmanagement.model.Event;
import com.ticketmaster.eventmanagement.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    EventsInfoClient client;

    public Artist retrieveArtistInfo(String artistId) {
        return mapArtist(artistId);
    }

    private Artist mapArtist(String artistId) {
        Artist artist = findArtistUsingId(artistId);
        artist.setEvents(mapEvents(artistId));
        return artist;
    }

    private Artist findArtistUsingId(String artistId) {
        return client.retrieveListOfArtist()
                .stream()
                .filter(artist -> artist.getId().equals(artistId))
                .findAny()
                .orElseThrow(() -> new UnMatchedException("There is no matching Artist Id"));
    }

    private List<Event> mapEvents(String artistId) {
        return client.retrieveListOfEvents()
                .stream()
                .filter(event -> event.getArtists().stream().anyMatch(artist -> artist.getId().equals(artistId)))
                .map(this::mapEvent)
                .collect(Collectors.toList());
    }

    private Event mapEvent(Event event) {
        event.setArtists(null);
        event.setVenue(mapVenueUsingId(event.getVenue().getId()));
        return event;
    }

    private Venue mapVenueUsingId(String venueId) {
        return client.retrieveListOfVenue()
                .stream()
                .filter(venue -> venue.getId().equals(venueId))
                .findAny()
                .orElseThrow(() -> new UnMatchedException("There is no matching Venue Id"));
    }
}
