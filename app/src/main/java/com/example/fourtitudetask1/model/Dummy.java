package com.example.fourtitudetask1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dummy implements Parcelable {
    private String title, subtitle, description;
    private String imageUrl;

    public Dummy() {
    }

    public Dummy(String title, String subtitle, String description, String imageUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public Dummy(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.title = data[0];
        this.subtitle = data[1];
        this.description = data[2];
        this.imageUrl = data[3];

//        // Deserialize Parcelable and cast to Bitmap first:
//        Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
//        // Convert Bitmap to Drawable:
//        this.image = new BitmapDrawable(bitmap);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                this.title,
                this.subtitle,
                this.description,
                this.imageUrl
        });

//        // Convert Drawable to Bitmap first:
//        Bitmap bitmap = (Bitmap)((BitmapDrawable) this.image).getBitmap();
//        // Serialize bitmap as Parcelable:
//        parcel.writeParcelable(bitmap, i);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Dummy createFromParcel(Parcel in) {
            return new Dummy(in);
        }

        public Dummy[] newArray(int size) {
            return new Dummy[size];
        }
    };
}
