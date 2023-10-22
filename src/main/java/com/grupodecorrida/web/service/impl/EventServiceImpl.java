package com.grupodecorrida.web.service.impl;

import com.grupodecorrida.web.dto.EventDto;
import com.grupodecorrida.web.models.Club;
import com.grupodecorrida.web.models.Event;
import com.grupodecorrida.web.repository.ClubRepository;
import com.grupodecorrida.web.repository.EventRepository;
import com.grupodecorrida.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.grupodecorrida.web.mapper.EventMapper.mapToEvent;
import static com.grupodecorrida.web.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> listEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList());
    }
}
