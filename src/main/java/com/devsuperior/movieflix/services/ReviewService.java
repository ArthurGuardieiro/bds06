package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        User user = authService.authenticated();
        Review entity = new Review();
        entity.setText(dto.getText());
        Optional<Movie> objMovie = movieRepository.findById(dto.getMovieId());
        Movie movie = objMovie.orElseThrow( () -> new ResourceNotFoundException("Entity movie not found"));
        entity.setMovie(movie);
        entity.setUser(user);
        entity = repository.save(entity);
        return new ReviewDTO(entity);
    }



}
