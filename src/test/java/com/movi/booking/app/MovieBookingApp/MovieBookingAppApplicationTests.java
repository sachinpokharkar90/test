package com.movi.booking.app.MovieBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.movi.booking.app.controller.HomeController;
import com.movi.booking.app.model.Movie;
import com.movi.booking.app.service.MovieService;

@SpringBootTest
class MovieBookingAppApplicationTests {

	private MovieService movieService;
	private HomeController homeController;
	private Model model;

	
	@BeforeEach
	public void setUp() {
		movieService = mock(MovieService.class);
		model = mock(Model.class);
		homeController = new HomeController(movieService);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testHomePage() throws IOException {
		List<Movie> mockMovies = List
				.of(new Movie("Inception", "Christopher Nolan", "Sci-Fi", LocalDate.now(), "A dream story."));

		when(movieService.loadMoviesFromJson()).thenReturn(mockMovies);

		String view = homeController.homePage(model);

		verify(model, times(1)).addAttribute("movies", mockMovies);
		assertEquals("home", view);
	}

	@Test
	public void testSearchPage() {
		String query = "Inception";
		List<Movie> result = List
				.of(new Movie("Inception", "Christopher Nolan", "Sci-Fi", LocalDate.now(), "A dream story."));

		when(movieService.searchMovies(query)).thenReturn(result);

		String view = homeController.searchPage(query, model);

		verify(model).addAttribute("movies", result);
		assertEquals("search", view);
	}

	@Test
	public void testMovieDetailPage() {
		String title = "Inception";
		Movie movie = new Movie(title, "Christopher Nolan", "Sci-Fi", LocalDate.now(), "A dream story.");

		when(movieService.getMovieDetails(title)).thenReturn(movie);

		String view = homeController.movieDetailPage(title, model);

		verify(model).addAttribute("movie", movie);
		assertEquals("movie", view);
	}

	@Test
	public void testMovieDetailPage_NotFound() {
		String title = "Unknown Movie";
		when(movieService.getMovieDetails(title)).thenReturn(null);

		String view = homeController.movieDetailPage(title, model);

		verify(model, never()).addAttribute(eq("movie"), any());
		assertEquals("movie", view); // Still returns "movie" view, but model has no movie
	}

}
