package base;

import actions.GenerateUser;
import actions.UserAction;
import constants.UserFields;
import io.qameta.allure.Step;
import resources.User;

public class BaseUser {
    private final GenerateUser generateUserData = new GenerateUser();
    public UserAction userAction = new UserAction();
    public User user;

    @Step("Создание и заполение карточки пользователя 'email + password + name'")
    public void generateEmailPassNameUserData() {
        generateUserData.generateEmailPassName();
        user = new User(
                generateUserData.getEmail(),
                generateUserData.getPassword(),
                generateUserData.getName());
    }

    @Step("Создание и заполение данных для изменения 'email + name'")
    public Object generateEmailNameUserData() {
        generateUserData.generateEmailPassName();
        user = new User(
                generateUserData.getEmail(),
                generateUserData.getName());
        return user;
    }

    @Step("Создание и заполнение карточки пользователя случайными данными")
    public void generateCustomUserData(String firstField, String secondField) {
        generateUserData.generateEmailPassName();
        user = new User();
        fillField(firstField);
        fillField(secondField);
    }

    private void fillField(String field) {
        switch (field) {
            case UserFields.EMAIL:
                user.setEmail(generateUserData.getEmail());
                break;
            case UserFields.PASSWORD:
                user.setPassword(generateUserData.getPassword());
                break;
            case UserFields.NAME:
                user.setName(generateUserData.getName());
                break;
        }
    }
}
