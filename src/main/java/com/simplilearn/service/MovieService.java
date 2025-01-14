package com.simplilearn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entity.Director;
import com.simplilearn.entity.Movie;
import com.simplilearn.repository.DirectorRepository;
import com.simplilearn.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	DirectorRepository directorRepository;

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		movieRepository.findAll().forEach(movie -> movies.add(movie));
		return movies;
	}

	public void saveMovie(Movie movie) {
		Optional<Director> directorOpt = directorRepository.findByDirectorName(movie.getDirector().getDirectorName());
		if(directorOpt.isPresent()) {
			movie.setDirector(directorOpt.get());
		}
		movieRepository.save(movie);
	}

	public void delete(int id) {
		movieRepository.deleteById(id);
	}
	
	public List<String> getMoviesByDirectorName(String name){
		List<Movie> movies = movieRepository.findMovieByDirectorName(name);
		List<String> movieNames = new ArrayList<String>();
		if(movieNames!= null) {
			movies.forEach(m -> movieNames.add(m.getName()));	
		}
		return movieNames;
	}
}
