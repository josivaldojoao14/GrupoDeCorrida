package com.grupodecorrida.web.dto;

import com.grupodecorrida.web.models.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    @NotEmpty(message = "O nome do evento não pode estar vazio")
    private String name;
    @NotEmpty(message = "A categoria do evento não pode estar vazia")
    private String category;
    @NotEmpty(message = "A URL de imagem do clube não pode estar vazia")
    private String imageUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;
}
