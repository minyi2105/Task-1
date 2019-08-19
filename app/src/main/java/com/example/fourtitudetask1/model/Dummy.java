package com.example.fourtitudetask1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Dummy> loadDummyList() {
        List<Dummy> dummyList = new ArrayList<>();

        List<String> listOfUrls = new ArrayList<>();
        listOfUrls.add("https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg");
        listOfUrls.add("https://mymodernmet.com/wp/wp-content/uploads/2019/07/russian-blue-cats-17-1024x1024.jpg");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/fruits.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/frymire.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/goldhill.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/monarch.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/mountain.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/zelda.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/peppers.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/pool.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/watch.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/tulips.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/sails.png");
        listOfUrls.add("https://homepages.cae.wisc.edu/~ece533/images/lena.png");

        for (int i = 0; i < listOfUrls.size(); i++) {
            int currentNumber = i + 1;

            Dummy dummy = new Dummy(
                    "Title " + currentNumber,
                    "Subtitle " + currentNumber + " Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                    "Description " + currentNumber + " Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Wwhen an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    listOfUrls.get(i));

            dummyList.add(dummy);
        }

        return dummyList;
    }

    public static Dummy[] populateData() {
        return new Dummy[] {
                new Dummy("image1.jpg", "title1", "text1", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg"),
                new Dummy("image2.jpg", "title2", "text2", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg"),
                new Dummy("image3.jpg", "title3", "text3", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg"),
                new Dummy("image4.jpg", "title4", "text4", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg"),
                new Dummy("image5.jpg", "title5", "text5", "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg")
        };
    }
}
