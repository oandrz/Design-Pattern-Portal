package creational.abstractfactory.main.factory;

import creational.abstractfactory.main.cheese.Cheese;
import creational.abstractfactory.main.cheese.MozzarellaCheese;
import creational.abstractfactory.main.dough.Dough;
import creational.abstractfactory.main.dough.ThickCrustDough;
import creational.abstractfactory.main.sauce.MarinaraSauce;
import creational.abstractfactory.main.sauce.Sauce;

public class NewYorkPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public String getType() {
        return "New York Style";
    }
}
