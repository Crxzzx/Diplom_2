package actions;


import constants.Endpoints;
import io.restassured.response.Response;
import model.IngredientsResponse;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class OrderAction extends BaseApi{
    public OrderAction() {
    }

    public Response getRequestIngredients() {
        return given(RequestSpecification())
                .when()
                .get(Endpoints.GET_INGREDIENTS);
    }

    public Response postRequestCreateOrderLogIn(Object obj, Object obj1) {
        return given(RequestSpecification())
                .header("Authorization", obj1)
                .body(obj)
                .when()
                .post(Endpoints.CREATE_ORDER);
    }

    public Response getRequestAllOrdersUserLogIn(Object obj) {
        return given(RequestSpecification())
                .header("Authorization", obj)
                .when()
                .get(Endpoints.GET_ORDERS);
    }

    public Response getRequestAllOrdersUserNotLogIn() {
        return given(RequestSpecification())
                .when()
                .get(Endpoints.GET_ORDERS);
    }

    public Response postRequestCreateOrderNotLogIn(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(Endpoints.CREATE_ORDER);
    }

    public List<String> getIngredients() {
        Response response = getRequestIngredients();
        var allIn = response.as(IngredientsResponse.class);
        var id = allIn.getData().stream().map(x -> x.getId()).collect(Collectors.toList());
        return id;
    }
}
