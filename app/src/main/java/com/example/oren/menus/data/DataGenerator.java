package com.example.oren.menus.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates data to pre-populate the database
 */
public class DataGenerator {

    private static final String[] FIRST = new String[]{
            "Mission: Impossible - Fallout",
            "Jurassic World: Fallen Kingdom",
            "Mission: Impossible - Rogue Nation",
            "Avengers: Infinity War",
            "Ant-Man and the Wasp" ,
            "Thor: Ragnarok",
            "Deadpool 2",
            "Ready Player One",
            "Insurgent",
            "Star Wars: The Last Jedi",
            "Black Panther",
            "Ghost Stories","Maleficent",
            "War for the Planet of the Apes",
            "The Maze Runner",
            "Ant-Man",
            "Divergent",
            "Tomb Raider",
            "Overboard",
            "Jurassic World",
            "Logan",
            "Blade Runner 2049",
            "Justice League",
            "Avengers: Age of Ultron",
            "The Lord of the Rings: The Fellowship of the Ring",
            "Skyscraper",
            "Wonder Woman",
            "The Avengers",
            "Pirates of the Caribbean: The Curse of the Black Pearl",
            "Mamma Mia! Here We Go Again",
            "Thor",
            "Thor: The Dark World",
            "Interstellar",
            "The Hobbit: The Battle of the Five Armies",
            "Maze Runner: The Death Cure",
            "Deadpool",
            "Geostorm",
            "X-Men: Apocalypse",
            "The Shape of Water",
            "Star Wars",
            "Inception",
            "Avatar",
            "Alice Through the Looking Glass",
            "Dawn of the Planet of the Apes",
            "The Dark Knight" ,
            "The Fate of the Furious",
            "Captain America: Civil War",
            "Game Night",
            "Solo: A Star Wars Story",
            "Fantastic Beasts and Where to Find Them",
            "The Shawshank Redemption",
            "Mad Max: Fury Road",
            "The Lord of the Rings: The Return of the King",
            "X-Men: Days of Future Past",
            "Pirates of the Caribbean: Dead Man's Chest",
            "Star Wars: The Force Awakens",
            "Guardians of the Galaxy"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey",
            "Rubber Chicken",
            "Pint of Grog",
            "Monocle"};
    private static final String[] GENRES = new String[]{
            "Action",
            "Adventure",
            "Animation",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "History",
            "Horror",
            "Music",
            "Mystery",
            "Romance",
            "Science Fiction",
            "TV Movie",
            "Thriller",
            "War",
            "Western"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here",
            "is the worst movie ever",
            "is the best movie ever",
            "is a faithful adaptation of a classic book",
            "is a terrible adaptation of an awful book",
            "was panned by audiences and critics alike",
            "was panned by critics but was well attended by audiences",
            "lacks a plot, conflict ",
            "not worth the price of admission",
            "took the best supporting actor at the academy awards",
            "took the best actor at the academy awards",
            "took the best actress at the academy awards",
            "took the best supporting actress at the academy awards",
            "took the best production design at the academy awards last year",
            "took the best production design at the academy awards this year",
            "took the best adapted screenplay at the academy awards",
            "took the best original screenplay at the academy awards",
            "lacks a sense of conflict",
            "is recommended by Stan S. Stanman",
            "is the best feature of the year",
            "is \uD83D\uDCAF", "is ❤️", "is fine"};
    public static List<Movie> generateMovies() {
        List<Movie> movies = new ArrayList<>(FIRST.length * SECOND.length);
        Random rnd = new Random();
        for (int i = 0; i < FIRST.length; i++) {
        //    for (int j = 0; j < SECOND.length; j++) {
                Movie movie = new Movie();
                movie.setTitle(FIRST[i]) ;
                movie.setBody(movie.getTitle() + " " + DESCRIPTION[i%DESCRIPTION.length]);
                movie.setRating(rnd.nextInt(10));
                movie.setWatched(rnd.nextInt(2));
                movie.setCategory(GENRES[i%GENRES.length]);
                movie.setId(FIRST.length * i  + 1);
                movie.setUri("http:\\\\www.tmdb.com\\pic\\"+movie.getId());
                Log.i("DataGenerator", "generateMovies: "+ movie.toString());
                movies.add(movie);
        //    }

        }
        return movies;
    }

}