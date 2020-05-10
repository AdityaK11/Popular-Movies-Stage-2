package com.aditya.popularmoviesstage2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aditya.popularmoviesstage2.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY voterAverage DESC")
    LiveData<List<Movie>> loadAllFavoriteMovies();

    @Query("SELECT isFavorite FROM movie WHERE id = :movieId")
    boolean isFavorite(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteMovie(Movie movie);

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :movieId")
    void updateFavoriteMovie(int movieId, boolean isFavorite);

    @Delete
    void deleteFavoriteMovie(Movie movie);

    @Query("SELECT * FROM movie WHERE id = :movieId")
    Movie getMovie(int movieId);
}
