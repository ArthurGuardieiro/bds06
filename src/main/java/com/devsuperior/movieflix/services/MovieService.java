package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDTO(entity);
    }

    public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
        genreId = (genreId == 0) ? null : genreId;
        Page<Movie> obj = repository.findByGenre(genreId, pageable);
        Page<MovieDTO> page = obj.map(x -> new MovieDTO(x));
        return page;
    }

    public List<ReviewDTO> getReviews(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieId(movieId);
        List<ReviewDTO> reviewDTOS = reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
        return reviewDTOS;
    }



}
