import base.BaseOrder;
import constants.ErrorMessagesAndSuccesses;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

public class CreateOrderTest extends BaseOrder {
    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c авторизацией")
    public void createOrderSuccessfulLogInTest() {
        generateEmailPassNameUserData();
        createOrderWithIngredients();
        Response response = orderAction.postRequestCreateOrderLogIn(order, userAction.getUserInfo(user));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без авторизации")
    public void createOrderSuccessfulWithoutLogInTest() {
        generateEmailPassNameUserData();
        createOrderWithIngredients();
        Response response = orderAction.postRequestCreateOrderNotLogIn(order);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders без ингредиентов")
    public void createOrderNotIngredients() {
        generateEmailPassNameUserData();
        createOrderWithoutIngredients();
        Response response = orderAction.postRequestCreateOrderLogIn(order, userAction.getUserInfo(user));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_TRANSFER_INGREDIENTS))
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Отправка корректного POST запроса /api/orders c передачей невалидного хеша ингредиента")
    public void createOrder() {
        generateEmailPassNameUserData();
        generateCustomUserData();
        Response response = orderAction.postRequestCreateOrderLogIn(order, userAction.getUserInfo(user));
        response.then().assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(user) == null) {
            userAction.deleteRequestRemoveUser(user);
        }
    }
}
