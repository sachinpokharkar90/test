package com.movi.booking.app.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movi.booking.app.model.Movie;
import com.movi.booking.app.repository.MovieRepository;

import jakarta.annotation.PostConstruct;

public class MovieService {

	
	private List<Movie> movieList;

	@Autowired
	private MovieRepository movieRepository;

	@PostConstruct
	public List<Movie> loadMoviesFromJson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// Load the JSON data from the file in resources
		movieList = objectMapper.readValue(new File("src/main/resources/movies.json"),
				new TypeReference<List<Movie>>() {
				});
		return movieList;
	}

	// Get Latest movie
	public List<Movie> getLatestMovies() {
		return movieRepository.findTop4ByOrderByReleaseDateDesc();
	}

	// Search Movies
	public List<Movie> searchMovies(String query) {
		return movieRepository.findByTitleContainingIgnoreCase(query);
	}

	// Get Movie by Title
	public Movie getMovieDetails(String title) {
		return movieRepository.findById(title).orElseThrow(() -> new RuntimeException("Movie not found"));
	}

	public Object getMovies() {
		// TODO Auto-generated method stub
		return null;
	}

}
