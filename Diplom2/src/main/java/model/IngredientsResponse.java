package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IngredientsResponse {
    @JsonProperty("data")
    private List<DataIngredients> data;

    @JsonProperty("success")
    private boolean success;

    public List<DataIngredients> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }
}
