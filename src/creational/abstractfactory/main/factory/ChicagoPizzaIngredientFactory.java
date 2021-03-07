package creational.abstractfactory.main.factory;

import creational.abstractfactory.main.cheese.Cheese;
import creational.abstractfactory.main.cheese.ReggianoCheese;
import creational.abstractfactory.main.dough.Dough;
import creational.abstractfactory.main.dough.ThinCrustDough;
import creational.abstractfactory.main.sauce.PlumTomatoSauce;
import creational.abstractfactory.main.sauce.Sauce;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public String getType() {
        return "Chicago";
    }
}
