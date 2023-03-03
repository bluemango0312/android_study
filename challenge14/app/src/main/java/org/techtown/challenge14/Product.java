package org.techtown.challenge14;

import android.graphics.drawable.Drawable;
import android.media.ImageReader;
import android.widget.ImageView;

public class Product {
    String name;
    String price;
    String description;
    int img;

    public Product(String name, String price, String description, int img) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
