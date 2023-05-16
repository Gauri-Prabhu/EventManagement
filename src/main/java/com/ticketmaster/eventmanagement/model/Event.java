package com.ticketmaster.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize
public class Event {

    @JsonProperty
    String title;
    @JsonProperty
    String id;
    @JsonProperty
    String timeZone;
    @JsonProperty
    String dateStatus;
    @JsonProperty
    List<Artist> artists = new ArrayList<>();
    @JsonProperty
    Venue venue;
    @JsonProperty
    boolean hiddenFromSearch;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(String dateStatus) {
        this.dateStatus = dateStatus;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public boolean isHiddenFromSearch() {
        return hiddenFromSearch;
    }

    public void setHiddenFromSearch(boolean hiddenFromSearch) {
        this.hiddenFromSearch = hiddenFromSearch;
    }
}
