package com.movi.booking.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movi.booking.app.model.Movie;
import com.movi.booking.app.service.MovieService;

@RestController("/movieBooking")
public class HomeController {
	

	private MovieService movieService;

	@Autowired
	public HomeController(MovieService movieService) {
		this.movieService = movieService;
	}

	// Home Page (Shows 4 most recent movies)
	@GetMapping("/")
	public String homePage(Model model) throws IOException {
		model.addAttribute("movies", movieService.loadMoviesFromJson());
		return "home";
	}

	// Search Page (Displays search results)
	@GetMapping("/search")
	public String searchPage(@RequestParam("query") String query, Model model) {
		model.addAttribute("movies", movieService.searchMovies(query));
		return "search";
	}

	@GetMapping("/movie/{title}")
	public String movieDetailPage(@PathVariable("title") String title, Model model) {
		Movie movie = movieService.getMovieDetails(title);
		if (movie != null) {
			model.addAttribute("movie", movie);
		}
		return "movie";
	}

}
