package com.grupodecorrida.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message = "O nome do clube não pode estar vazio")
    private String name;
    @NotEmpty(message = "A URL de imagem do clube não pode estar vazia")
    private String imageUrl;
    @NotEmpty(message = "A descrição do clube não pode estar vazia")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
