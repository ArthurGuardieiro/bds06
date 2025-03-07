package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ReviewDTO {

    private Long id;
    @NotBlank(message = "campo requerido")
    private String text;
    @Positive(message = "id tem que ser positivo")
    private Long movieId;
    private UserDTO user;

    public ReviewDTO() {

    }

    public ReviewDTO(Long id, String text, Long movieId, UserDTO user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = user;
    }

    public ReviewDTO(Review entity) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.movieId = entity.getMovie().getId();
        this.user = new UserDTO(entity.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
