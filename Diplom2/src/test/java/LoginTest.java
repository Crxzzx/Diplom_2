import base.BaseUser;
import constants.ErrorMessagesAndSuccesses;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LoginTest extends BaseUser {
    @Test
    @DisplayName("Отправка корректного POST запроса /api/auth/login")
    public void logInUserSuccessfulPathTest() {
        generateEmailPassNameUserData();
        userAction.postRequestCreateUser(user);
        Response response = userAction.postRequestLogIn(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .and().assertThat().body("accessToken", Matchers.notNullValue())
                .and().assertThat().body("refreshToken", Matchers.notNullValue())
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/login c неверными или несуществующими данными")
    public void noSuccessLogInUserTest() {
        generateEmailPassNameUserData();
        user.setEmail("proverka@mail.com");
        user.setPassword("qwerty");
        Response response = userAction.postRequestLogIn(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.INCORRECT_LOGIN_OR_PASSWORD))
                .and().statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(user) != null) {
            userAction.deleteRequestRemoveUser(user);
        }
    }
}
