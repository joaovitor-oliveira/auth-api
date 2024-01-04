package com.joaovitor.authapi.dto.anime;

import com.joaovitor.authapi.domain.anime.Anime;
import jakarta.validation.constraints.NotBlank;

public record AnimeRequestDTO(
        @NotBlank
        String name
) {
        public Anime toEntity() {
                return new Anime(this.name);
        }
}
