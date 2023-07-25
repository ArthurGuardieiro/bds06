package com.devsuperior.movieflix.controller;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<Page<MovieDTO>> findByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
        Page <MovieDTO> page = service.findByGenre(genreId, pageRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findAllByMovieId(@PathVariable Long id) {
        List<ReviewDTO> dto = service.getReviews(id);
        return ResponseEntity.ok(dto);
    }

}
