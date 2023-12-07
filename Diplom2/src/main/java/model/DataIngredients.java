package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataIngredients {
    @JsonProperty("carbohydrates")
    private int carbohydrates;

    @JsonProperty("image")
    private String image;

    @JsonProperty("proteins")
    private int proteins;

    @JsonProperty("price")
    private int price;

    @JsonProperty("__v")
    private int v;

    @JsonProperty("name")
    private String name;

    @JsonProperty("fat")
    private int fat;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("calories")
    private int calories;

    @JsonProperty("type")
    private String type;

    @JsonProperty("image_mobile")
    private String imageMobile;

    @JsonProperty("image_large")
    private String imageLarge;

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public String getImage() {
        return image;
    }

    public int getProteins() {
        return proteins;
    }

    public int getPrice() {
        return price;
    }

    public int getV() {
        return v;
    }

    public String getName() {
        return name;
    }

    public int getFat() {
        return fat;
    }

    public String getId() {
        return id;
    }

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }

    public String getImageMobile() {
        return imageMobile;
    }

    public String getImageLarge() {
        return imageLarge;
    }
}
