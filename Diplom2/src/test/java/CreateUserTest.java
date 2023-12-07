import base.BaseUser;
import constants.ErrorMessagesAndSuccesses;
import constants.UserFields;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;


import static org.apache.http.HttpStatus.*;

public class CreateUserTest extends BaseUser {
    @Test
    @DisplayName("Отправка корректного POST запроса /api/auth/register")
    public void createUserSuccessfulPathTest() {
        generateEmailPassNameUserData();
        Response response = userAction.postRequestCreateUser(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .and().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register дважды")
    public void createUserTwiceTest() {
        generateEmailPassNameUserData();
        userAction.postRequestCreateUser(user);
        Response response = userAction.postRequestCreateUser(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.CREATE_REPEAT_USER))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля email")
    public void createUserEmailNullValueTest() {
        generateCustomUserData(UserFields.PASSWORD , UserFields.NAME);
        Response response = userAction.postRequestCreateUser(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.CREATE_WITHOUT_REQUIRED_FIELD))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля password")
    public void createUserPasswordNullValueTest() {
        generateCustomUserData(UserFields.EMAIL, UserFields.NAME);
        Response response = userAction.postRequestCreateUser(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.CREATE_WITHOUT_REQUIRED_FIELD))
                .and().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка POST запроса /api/auth/register без поля name")
    public void createUserNameNullValueTest() {
        generateCustomUserData(UserFields.EMAIL, UserFields.PASSWORD);
        Response response = userAction.postRequestCreateUser(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.CREATE_WITHOUT_REQUIRED_FIELD))
                .and().statusCode(SC_FORBIDDEN);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(user) != null) {
            userAction.deleteRequestRemoveUser(user);
        }
    }
}
