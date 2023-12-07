package actions;

import constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.User;

import static io.restassured.RestAssured.given;

public class UserAction extends BaseApi{
    public UserAction() {
    }

    public Response postRequestCreateUser(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(Endpoints.CREATE_USER);
    }

    public Response postRequestLogIn(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .post(Endpoints.LOGIN_USER);
    }

    public Response getRequestUserInfo(Object obj) {
        return given(RequestSpecification())
                .header("Authorization", obj)
                .when()
                .get(Endpoints.GET_USER);
    }

    public Response patchRequestUserInfo(Object obj, Object obj1) {
        return given(RequestSpecification())
                .header("Authorization", obj)
                .body(obj1)
                .when()
                .patch(Endpoints.GET_USER);
    }

    public Response patchRequestUserInfoNotToken(Object obj) {
        return given(RequestSpecification())
                .body(obj)
                .when()
                .patch(Endpoints.GET_USER);
    }

    @Step("Удаление пользователя")
    public Response deleteRequestRemoveUser(User user) {
        return deleteRequestRemoveUserToken(getUserToken(user));
    }

    //    @Step("Узнать токен пользователя по его логину и паролю")
    public String getUserToken(User user) {
        Response response = postRequestLogIn(user);
        return response.jsonPath().getString("accessToken");
    }

    @Step("Получить токен")
    public String getUserInfo(User user) {
        Response response = postRequestCreateUser(user);
        return response.jsonPath().getString("accessToken");
    }

    @Step("Удалить пользователя, DELETE запрос - /api/auth/user + токен")
    public Response deleteRequestRemoveUserToken(String userToken) {
        return given(RequestSpecification())
                .header("Authorization", userToken)
                .delete(Endpoints.GET_USER);
    }
}
