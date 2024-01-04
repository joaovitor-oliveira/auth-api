package com.joaovitor.authapi.repository.anime;

import com.joaovitor.authapi.domain.anime.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, UUID> {
}
