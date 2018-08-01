package com.example.oren.menus.data;

public class MovieConstants {


    /////  dbase ////////////////////////////////////////////////

    public static final String DATABASE_NAME            = "movies.db";
    public static final String TABLE_NAME               = "movies";
    public static final int    DATABASE_USERS_VERSION   = 1;

    ///// columns ///////////////////////////////////////////////

    public static final String MOVIE_ID_COLUMN          = "_id";

    public static final String MOVIE_WEB_ID_COLUMN      = "id";
    public static final String TMDBM_WEB_ID_COLUMN      = "id";

    public static final String MOVIE_TITLE_COLUMN       = "title";
    public static final String TMDBM_TITLE_COLUMN       = "title";

    public static final String MOVIE_IMAGE_URI_COLUMN   = "uri";
    public static final String TMDB_IMAGE_URI_COLUMN    = "poster_path";

    public static final String MOVIE_BODY_COLUMN        = "body";
    public static final String TMDB_BODY_COLUMN         = "overview";

    public static final String MOVIE_CATEGORY_COLUMN    = "category";
    public static final String TMDB_CATEGORY_COLUMN    = "genres";

    public static final String MOVIE_RATING_COLUMN      = "rating";
    public static final String TMDB_RATING_COLUMN      = "vote_average";

    public static final String MOVIE_WATCHED_COLUMN     = "watched";

}

/*{
    "adult":false,
    "backdrop_path":"/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
    "belongs_to_collection":null,"budget":63000000,
    "genres":[{"id":18,"name":"Drama"}],
    "homepage":"http://www.foxmovies.com/movies/fight-club",
    "id":550,
    "imdb_id":"tt0137523",
    "original_language":"en",
    "original_title":"Fight Club",
    "overview":"A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    "popularity":35.727,
    "poster_path":"/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
    "production_companies":[
        {"id":508,"logo_path":"/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png","name":"Regency Enterprises","origin_country":"US"},
        {"id":711,"logo_path":"/tEiIH5QesdheJmDAqQwvtN60727.png","name":"Fox 2000 Pictures","origin_country":"US"},
        {"id":20555,"logo_path":null,"name":"Taurus Film","origin_country":""},
        {"id":54051,"logo_path":null,"name":"Atman Entertainment","origin_country":""},
        {"id":54052,"logo_path":null,"name":"Knickerbocker Films","origin_country":""},
        {"id":25,"logo_path":"/qZCc1lty5FzX30aOCVRBLzaVmcp.png","name":"20th Century Fox","origin_country":"US"},
        {"id":4700,"logo_path":"/A32wmjrs9Psf4zw0uaixF0GXfxq.png","name":"The Linson Company","origin_country":""}],
    "production_countries":[
            {"iso_3166_1":"DE","name":"Germany"},
            {"iso_3166_1":"US","name":"United States of America"}],
    "release_date":"1999-10-15",
    "revenue":100853753,
    "runtime":139,
    "spoken_languages":[{"iso_639_1":"en","name":"English"}],
    "status":"Released",
    "tagline":"Mischief. Mayhem. Soap.",
    "title":"Fight Club",
    "video":false,
    "vote_average":8.4,
    "vote_count":13095
}*/
