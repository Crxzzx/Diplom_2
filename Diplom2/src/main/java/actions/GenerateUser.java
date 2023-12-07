package actions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
public class GenerateUser {
    private String email;
    private String password;
    private String name;

    private String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public void generateEmailPassName() {
        email = generateRandomString() + "@mail.com";
        password = generateRandomString();
        name = generateRandomString();
    }
}
