package com.ticketmaster.eventmanagement;

import com.ticketmaster.eventmanagement.model.Artist;
import com.ticketmaster.eventmanagement.model.Event;
import com.ticketmaster.eventmanagement.model.Venue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventsInfoClient {
    RestTemplate restTemplate = new RestTemplateBuilder().build();

    public List<Event> retrieveListOfEvents() {
        Event[] responseOfEvents = restTemplate.getForObject("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json", Event[].class);
        return Arrays.stream(responseOfEvents).collect(Collectors.toList());
    }

    public List<Artist> retrieveListOfArtist() {
        Artist[] responseOfEvents = restTemplate.getForObject("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json", Artist[].class);
        return Arrays.stream(responseOfEvents).collect(Collectors.toList());
    }

    public List<Venue> retrieveListOfVenue() {
        Venue[] responseOfEvents = restTemplate.getForObject("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/venues.json", Venue[].class);
        return Arrays.stream(responseOfEvents).collect(Collectors.toList());
    }
}
