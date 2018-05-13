package com.karla00058615.labo6.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Karla on 13/5/2018.
 */

public class Planets implements Parcelable{
    private int id;
    private String name;
    private boolean favorite;

    public Planets(int id, String name, boolean favorite) {
        this.id = id;
        this.name = name;
        this.favorite = favorite;
    }

    protected Planets(Parcel in) {
        id = in.readInt();
        name = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Planets> CREATOR = new Creator<Planets>() {
        @Override
        public Planets createFromParcel(Parcel in) {
            return new Planets(in);
        }

        @Override
        public Planets[] newArray(int size) {
            return new Planets[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }
}
