package com.example.fourtitudetask1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dummy_table")
public class Dummy implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "subtitle")
    private String subtitle;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    public Dummy() {
    }

    public Dummy(String title, String subtitle, String description, String imageUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
    }

    protected Dummy(Parcel in) {
        this.title = in.readString();
        this.subtitle = in.readString();
        this.description = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Creator<Dummy> CREATOR = new Creator<Dummy>() {
        @Override
        public Dummy createFromParcel(Parcel source) {
            return new Dummy(source);
        }

        @Override
        public Dummy[] newArray(int size) {
            return new Dummy[size];
        }
    };
}
