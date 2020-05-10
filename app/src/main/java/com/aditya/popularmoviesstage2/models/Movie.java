package com.aditya.popularmoviesstage2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie implements Parcelable{

    @PrimaryKey
    private Integer id;
    private String title;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private Double voterAverage;
    private boolean isFavorite;

    public String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    @Ignore
    public Movie() {

    }

    public Movie(Integer id, String title, String posterPath, String overview, String releaseDate, Double voterAverage, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voterAverage = voterAverage;
        this.isFavorite = isFavorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoterAverage() {
        return voterAverage;
    }

    public void setVoterAverage(Double voterAverage) {
        this.voterAverage = voterAverage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeDouble(voterAverage);
    }

    public Movie(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        posterPath = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        voterAverage = parcel.readDouble();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel src) {
            return new Movie(src);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}