package com.grupodecorrida.web.controller;

import com.grupodecorrida.web.dto.ClubDto;
import com.grupodecorrida.web.models.Club;
import com.grupodecorrida.web.models.UserEntity;
import com.grupodecorrida.web.security.SecurityUtil;
import com.grupodecorrida.web.service.ClubService;
import com.grupodecorrida.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;
    private final UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();

        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();

        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteById(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto club, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-create";
        }

        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-edit";
        }

        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
