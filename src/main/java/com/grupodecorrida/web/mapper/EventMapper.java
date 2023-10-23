package com.grupodecorrida.web.mapper;

import com.grupodecorrida.web.dto.EventDto;
import com.grupodecorrida.web.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .category(eventDto.getCategory())
                .imageUrl(eventDto.getImageUrl())
                .club(eventDto.getClub())
                .build();
    }

    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .category(event.getCategory())
                .imageUrl(event.getImageUrl())
                .club(event.getClub())
                .build();
    }
}
