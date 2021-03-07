package creational.abstractfactory.main.factory;

import creational.abstractfactory.main.cheese.Cheese;
import creational.abstractfactory.main.dough.Dough;
import creational.abstractfactory.main.sauce.Sauce;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    String getType();
}
