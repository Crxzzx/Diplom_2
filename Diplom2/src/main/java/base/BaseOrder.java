package base;

import actions.GenerateOrder;
import actions.GenerateUser;
import actions.OrderAction;
import actions.UserAction;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import resources.Order;
import resources.User;

import static constants.OrderFields.INGREDIENTS_1;

@Getter
@Setter
public class BaseOrder {
    protected final GenerateOrder generateOrder = new GenerateOrder();
    private final GenerateUser generateUser = new GenerateUser();

    public OrderAction orderAction = new OrderAction();
    public UserAction userAction = new UserAction();
    public Order order;
    public User user;

    @Step("Создание заказа используяю все ингредиенты")
    public void createOrderWithIngredients() {
        generateOrder.IdIngredients();
        order = new Order(
                generateOrder.getIngredients()
        );
    }

    @Step("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {
        order = new Order(
                generateOrder.getIngredients()
        );
    }

    @Step("Создание и заполнение карточки заказа с неверным хешем ингредиентов (при помощи полей)")
    public void generateCustomUserData() {
        order = new Order(INGREDIENTS_1);
    }

    @Step("Создание и заполение карточки пользователя (email + password + name)")
    public void generateEmailPassNameUserData() {
        generateUser.generateEmailPassName();
        user = new User(
                generateUser.getEmail(),
                generateUser.getPassword(),
                generateUser.getName());
    }
}
