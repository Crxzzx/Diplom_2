package actions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateOrder {
    OrderAction orderAction = new OrderAction();
    private List<String> ingredients;

    public void IdIngredients() {
        ingredients = orderAction.getIngredients();
    }
}
