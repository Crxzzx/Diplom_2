package resources;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@JsonIncludeProperties({"accessToken"})
@Accessors(chain = true)
public class UserToken {
    private String accessToken;

    public UserToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserToken() {
    }
}
