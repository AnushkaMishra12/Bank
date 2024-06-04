package com.example.bank;

public class trend_data {

    int image;
    String title;

    public trend_data(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
