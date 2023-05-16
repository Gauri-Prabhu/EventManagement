package com.ticketmaster.eventmanagement.controller;

import com.ticketmaster.eventmanagement.model.Artist;
import com.ticketmaster.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Event Information controller
 * This can be used to fetch details of Artist using id.
 */
@RestController
public class EventInfoController {
    @Autowired
    EventService service;

    @RequestMapping(path = "/artist/{artistId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Artist> getArtistInformation(@PathVariable(name = "artistId") String artistId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.retrieveArtistInfo(artistId));
    }
}
