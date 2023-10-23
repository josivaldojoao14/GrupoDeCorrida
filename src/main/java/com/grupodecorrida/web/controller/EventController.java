package com.grupodecorrida.web.controller;

import com.grupodecorrida.web.dto.EventDto;
import com.grupodecorrida.web.models.Event;
import com.grupodecorrida.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        List<EventDto> events = eventService.listEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long clubId, Model model) {
        EventDto event = eventService.findEventById(clubId);
        model.addAttribute("event", event);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") Long clubId, Model model) {
        EventDto eventDto = eventService.findEventById(clubId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteById(eventId);
        return "redirect:/events";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, Model model, @Valid
                            @ModelAttribute("event") EventDto event, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }
        EventDto eventDto = eventService.findEventById(eventId);

        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, Model model, @Valid
                              @ModelAttribute("event") EventDto event, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", event);
            return "events-edit";
        }
        eventService.createEvent(clubId, event);
        return "redirect:/clubs/" + clubId;
    }
}
