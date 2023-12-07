import base.BaseUser;
import constants.ErrorMessagesAndSuccesses;
import constants.UserFields;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import resources.User;

import static org.apache.http.HttpStatus.*;

public class ChangingUserDataTest extends BaseUser {
    @Test
    @DisplayName("Отправка GET запроса /api/auth/user для получения данных о пользователе")
    public void getUserInfoTest() {
        generateEmailPassNameUserData();
        Response response = userAction.getRequestUserInfo(userAction.getUserInfo(user));
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .and().assertThat().body("user.email", Matchers.equalTo(user.getEmail().toLowerCase()))
                .and().assertThat().body("user.name", Matchers.equalTo(user.getName()))
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user для изменения данных о пользователе")
    public void patchUserInfoTest() {
        generateEmailPassNameUserData();
        String password = user.getPassword();
        Response response = userAction.patchRequestUserInfo(userAction.getUserInfo(user), generateEmailNameUserData());
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .and().assertThat().body("user.email", Matchers.equalTo(user.getEmail().toLowerCase()))
                .and().assertThat().body("user.name", Matchers.equalTo(user.getName()))
                .statusCode(SC_OK);
        user.setPassword(password);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user с использованием занятой почты")
    public void patchUserInfoEmailAlreadyUseTest() {
        generateEmailPassNameUserData();
        User alreadyEmailUse = new User(UserFields.USED_EMAIL, UserFields.USED_NAME);
        Response response = userAction.patchRequestUserInfo(userAction.getUserInfo(user), alreadyEmailUse);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.EMAIL_ALREADY_USE))
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Отправка PATCH запроса /api/auth/user без авторизации")
    public void patchUserInfoNotTokenTest() {
        generateEmailPassNameUserData();
        Response response = userAction.patchRequestUserInfoNotToken(user);
        userAction.getUserInfo(user);
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.NOT_SUCCESS))
                .and().assertThat().body("message", Matchers.equalTo(ErrorMessagesAndSuccesses.NONE_LOGIN_PATCH_USER))
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    public void deleteUser() {

        if (userAction.getUserToken(user) != null) {
            userAction.deleteRequestRemoveUser(user);
        }
    }
}
