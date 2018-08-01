package com.example.oren.menus.data;

//import java.util.ArrayList;
import java.util.List;

public class SampleData {


    public static List<Movie> getMovies() {

        return DataGenerator.generateMovies();
    }
}
