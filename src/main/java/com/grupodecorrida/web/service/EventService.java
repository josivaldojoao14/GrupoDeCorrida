package com.grupodecorrida.web.service;

import com.grupodecorrida.web.dto.EventDto;
import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    EventDto findEventById(Long clubId);
    List<EventDto> listEvents();

    void updateEvent(EventDto eventDto);

    void deleteById(Long eventId);
}
