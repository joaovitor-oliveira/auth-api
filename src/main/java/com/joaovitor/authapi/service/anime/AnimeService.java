package com.joaovitor.authapi.service.anime;

import com.joaovitor.authapi.domain.anime.Anime;
import com.joaovitor.authapi.repository.anime.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }
}
