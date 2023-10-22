package com.grupodecorrida.web.service;

import com.grupodecorrida.web.dto.EventDto;
import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> listEvents();
}
