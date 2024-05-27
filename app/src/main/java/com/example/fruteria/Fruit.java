package com.example.fruteria;

public class Fruit {
    private String name;
    private int imageResId;
    private int quantity;

    public Fruit(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


