package com.movi.booking.app.model;

import java.time.LocalDate;

public class Movie {

	private String title;
	private String director;
	private LocalDate releaseDate;
	private String genre;
	private String description;

	public Movie(String title, String director, LocalDate releaseDate, String genre, String description) {
		this.title = title;
		this.director = director;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
