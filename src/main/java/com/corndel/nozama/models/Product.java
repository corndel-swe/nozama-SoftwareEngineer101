package com.corndel.nozama.models;

public class Product {
    private final Integer id;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private String imageURL;

        public Product(Integer id, String name, String description, float price, int stockQuantity, String imageURL) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.imageURL = imageURL;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public float getPrice() {return price;}

        public void setPrice(float price) {
            this.price = price;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(Integer stockQuantity) {this.stockQuantity = stockQuantity;}

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
}
