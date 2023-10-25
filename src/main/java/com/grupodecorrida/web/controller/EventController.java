package com.grupodecorrida.web.controller;

import com.grupodecorrida.web.dto.EventDto;
import com.grupodecorrida.web.models.Event;
import com.grupodecorrida.web.models.UserEntity;
import com.grupodecorrida.web.security.SecurityUtil;
import com.grupodecorrida.web.service.EventService;
import com.grupodecorrida.web.service.UserService;
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
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        UserEntity user = new UserEntity();
        List<EventDto> events = eventService.listEvents();
        String username = SecurityUtil.getSessionUser();

        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
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
        UserEntity user = new UserEntity();
        EventDto eventDto = eventService.findEventById(clubId);
        String username = SecurityUtil.getSessionUser();

        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("club", eventDto);
        model.addAttribute("user", user);
        model.addAttribute("event", eventDto);
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
