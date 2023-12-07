import base.BaseOrder;
import constants.ErrorMessagesAndSuccesses;
import org.hamcrest.Matchers;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;

import static org.apache.http.HttpStatus.*;
public class GetOrdersOfUserTest extends BaseOrder {
    @Test
    @DisplayName("Отправка корректного GET запроса /api/orders c авторизацией")
    public void getOrderUserLogInTest() {
        generateEmailPassNameUserData();
        createOrderWithIngredients();
        orderAction.postRequestCreateOrderLogIn(order, userAction.getUserInfo(user));
        Response response = orderAction.getRequestAllOrdersUserLogIn(userAction.getUserToken(user));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка корректного GET запроса /api/orders без авторизации")
    public void getOrderUserWithoutLogInTest() {
        generateEmailPassNameUserData();
        createOrderWithIngredients();
        orderAction.postRequestCreateOrderLogIn(order, userAction.getUserInfo(user));
        Response response = orderAction.getRequestAllOrdersUserNotLogIn();
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(user) != null) {
            userAction.deleteRequestRemoveUser(user);
        }
    }
}
