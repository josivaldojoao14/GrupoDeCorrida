package com.grupodecorrida.web.service;

import com.grupodecorrida.web.dto.ClubDto;
import com.grupodecorrida.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(Long clubId);
    void updateClub(ClubDto club);
    void deleteById(Long clubId);

    List<ClubDto> searchClubs(String query);
}
