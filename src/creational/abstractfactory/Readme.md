# Creational Pattern 1 - Abstract Factory

Intent of the Design Pattern
-
From `Design Pattern: Element Reusable` by Gang Of Four
> Provide an interface for creating families of related or dependent objects without specifying their concrete class

How do we implement the Pattern? [Complete Diagram](https://drive.google.com/file/d/1lequ0JLwheh6gX2G7fHBhRVUpk2xRX3Q/view?usp=sharing)
- 
- Explicitly declare an interface for each distinct product.

  [![abstract_factory_step_1.png](https://i.postimg.cc/3RpysZj7/Screenshot-2021-03-07-at-10-18-26-AM.png)](https://postimg.cc/vgYH9fSS)  

- Make the specific variant of those distinct product and implement the interface.
  
  
  [![abstract_factory_step_2.png](https://i.postimg.cc/mrsK3Ldw/Screenshot-2021-03-07-at-10-34-41-AM.png)](https://postimg.cc/Hr6Sdg1c)
  
- Declare `abstract factory` (interface with a list of creation method for all products). The factory should return the 
  abstract product type represented by the interface that we made earlier.

  [![Screenshot-2021-03-07-at-10-42-25-AM.png](https://i.postimg.cc/q76X7ycD/Screenshot-2021-03-07-at-10-42-25-AM.png)](https://postimg.cc/06xJW6B7)

- Create separate factory for each variant of the products.

[![Screenshot-2021-03-07-at-10-45-01-AM.png](https://i.postimg.cc/3xs0n6p9/Screenshot-2021-03-07-at-10-45-01-AM.png)](https://postimg.cc/JsQhhppB)

When do we use the Design Pattern?
-
The instance that you're trying to create have `multiple families of products`
and each family of product `have their own variant`.

Structure
- 

[![Screenshot-2021-03-07-at-10-38-34-AM.png](https://i.postimg.cc/bNR6XxLQ/Screenshot-2021-03-07-at-10-38-34-AM.png)](https://postimg.cc/N558mr3M)

Pros and Cons
-
Pros | Cons
--- | ---
Isolates clients from implementation classes | If there's a new kinds of products, you need to added it into abstract factory class and all of its subclasses
Abstract Factory creates a complete family of products which makes easier to exchange into another variant | 
Abstract factory enforce usage of object from only on variant at a time | 

Example Case Problem
-  
Imagine you're creating pizza store simulator shop, your code consist of classes that represent:
- A family of related ingredient to make a pizza, for example: `Dough`, `Sauce`, `Cheese`.
- Several variants of this family, for example: `Dough` + `Sauce` + `Cheese` are available in these variants: `NewYork Pizza Style` and `Chicago Pizza Style`
You need a way to create individual pizza objects for New York and Chicago style to fulfil your consumer order.
You don't want to change existing code when adding a new Pizza Style or Ingredients to the program because we always innovate our pizza store's menu to serve customer better.
  
Solution of the Case Problem
- 
- Create Interface for each product, in this case we will going to create interface for `Dough`, `Sauce`and `Cheese`.
```java
public interface Cheese {
    String getType();
}
public interface Dough {
  String getType();
}
public interface Sauce {
  String getType();
}
```
- Create the specific variant of the product, in this case we are going to have `ThickCrustDough` and `ThinCrustDough` 
  for `Dough`, `MarinaraSauce` and `PlumTomatoSauce` for `Sauce`, `MozzarellaCheese` and `ReggianoCheese` for `Cheese`
```java
// For Cheese
public class MozzarellaCheese implements Cheese {
    @Override
    public String getType() {
        return "Mozarella Cheese";
    }
}
public class ReggianoCheese implements Cheese {
  @Override
  public String getType() {
    return "Reggiano Cheese";
  }
}

// For Dough
public class ThickCrustDough implements Dough {
  @Override
  public String getType() {
    return "Thick Crust";
  }
}
public class ThinCrustDough implements Dough {
  @Override
  public String getType() {
    return "Thin Crust";
  }
}

// For Sauce
public class MarinaraSauce implements Sauce {
  @Override
  public String getType() {
    return "Marinara Sauce";
  }
}
public class PlumTomatoSauce implements Sauce {
  @Override
  public String getType() {
    return "Plum Tomato Sauce";
  }
}
```
- Create an `abstract factory` in this case we're going to make `PizzaIngredientFactory`
```java
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    String getType();
}
```

- Create separate factory for each variant in the group
```java
//New York Style Factory
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

// Chicago Style Factory
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
```

Implementation Technique
--
- Factories as `Singleton`, because typically an application needs only one instance of a ConcreteFactory per product variant.
```java
// Chicago Style Become Singleton
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
  
  private ChicagoPizzaIngredientFactory INSTANCE;
  
  private ChicagoPizzaIngredientFactory() {}

  public static ChicagoPizzaIngredientFactory getInstance() {
      if (INSTANCE == null) {
          INSTANCE = new ChicagoPizzaIngredientFactory()
      }
      return INSTANCE;
  }
  
  //...

  @Override
  public String getType() {
    return "Chicago";
  }
}

// Usage
ChicagoPizzaIngredientFactory.getInstance().createDough()
```
- Use `Factory Method` to create the concrete product. While it's simple the trade off doing that is it requires a new 
  concrete factory subclass for each product family, even if the product family differ only slightly.
- If you want your factory to be more flexible so that when you add the new product you don't need to do any changes in 
  the abstract factory interface and all classes that depend on it, you can use some kind of `parameter` that specifies 
  what kind of object that you want to create.
```java
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
  
  /*
   * In CHICAGO_INGREDIENT_DICTIONARY we already map the ingredients based on the type.
   * */
  @Override
  public Ingredient createIngredient(IngredientType ingredientType) {
    return CHICAGO_INGREDIENT_DICTIONARY.get(ingredientType);
  }

  @Override
  public String getType() {
    return "Chicago";
  }
}
```
