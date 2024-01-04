package com.joaovitor.authapi.dto.anime;

import com.joaovitor.authapi.domain.anime.Anime;

public record AnimeResponseDTO(String id, String name) {
    public static AnimeResponseDTO fromEntity(Anime anime) {
        return new AnimeResponseDTO(
                anime.getId(),
                anime.getName()
        );
    }
}
