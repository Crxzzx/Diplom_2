import base.BaseOrder;
import constants.ErrorMessagesAndSuccesses;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class GetIngredientsTest extends BaseOrder {
    @Test
    @DisplayName("Отправка GET запроса /api/ingredients для получения данных об ингредиентах")
    public void getIngredientsTest() {
        Response response = orderAction.getRequestIngredients();
        response.then().assertThat().body("success", Matchers.equalTo(ErrorMessagesAndSuccesses.SUCCESS))
                .statusCode(SC_OK);
    }
}
