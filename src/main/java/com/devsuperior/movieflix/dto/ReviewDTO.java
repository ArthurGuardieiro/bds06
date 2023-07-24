package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO {

    private String text;
    private Long movieId;

    public ReviewDTO() {

    }

    public ReviewDTO(String text, Long movieId) {
        this.text = text;
        this.movieId = movieId;
    }

    public ReviewDTO(Review entity) {
        this.text = entity.getText();
        this.movieId = entity.getMovie().getId();
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
}
