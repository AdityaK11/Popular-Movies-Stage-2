package com.aditya.popularmoviesstage2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable {

    private String key;
    private String name;
    private String site;

    public Trailer() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    public Trailer(Parcel in) {
        key = in.readString();
        name = in.readString();
        site = in.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(name);
        parcel.writeString(site);
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
}
