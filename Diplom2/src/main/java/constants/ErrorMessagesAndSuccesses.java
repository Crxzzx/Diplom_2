package constants;

public class ErrorMessagesAndSuccesses {
    public final static String CREATE_REPEAT_USER = "User already exists";
    public final static String CREATE_WITHOUT_REQUIRED_FIELD = "Email, password and name are required fields";
    public final static String INCORRECT_LOGIN_OR_PASSWORD = "email or password are incorrect";
    public final static String NONE_LOGIN_PATCH_USER = "You should be authorised";
    public final static String EMAIL_ALREADY_USE = "User with such email already exists";
    public final static String NOT_TRANSFER_INGREDIENTS = "Ingredient ids must be provided";
    public final static Boolean SUCCESS = true;
    public final static Boolean NOT_SUCCESS = false;
}
