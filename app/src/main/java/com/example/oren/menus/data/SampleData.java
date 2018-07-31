package com.example.oren.menus.data;

import java.util.ArrayList;
import java.util.List;

public class SampleData {


    public static List<Movie> getMovies() {

        List<Movie> movies = new ArrayList<>();


        movies.add(new Movie( "title1",  "body1", "http://uri.com//1", 2,  1,  "category1") );
        movies.add(new Movie( "title2",  "body2", "http://uri.com//2", 2,  3,  "category2") );
        movies.add(new Movie( "title3",  "body3", "http://uri.com//3", 3,  1,  "category3") );
        movies.add(new Movie( "title4",  "body4", "http://uri.com?/4", 4,  3,  "category4") );
        return movies;
    }
}
