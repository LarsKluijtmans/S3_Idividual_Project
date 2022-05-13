package com.example.individualproject.dto.genre;

import com.example.individualproject.repository.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetGenreDTO {

    private Long id;
    private String genre;

    public GetGenreDTO(Genre genre) {
        this.id = genre.getId();
        this.genre = genre.getGenre();
    }
}

