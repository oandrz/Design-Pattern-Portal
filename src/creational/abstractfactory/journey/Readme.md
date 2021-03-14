# Abstract Factory Journey, from zero to Abstract Factory

Reference: [From Head First Design Pattern, Page 147](https://www.amazon.sg/Head-First-Design-Patterns-Freeman/dp/0596007124/ref=sr_1_2?adgrpid=101389983568&dchild=1&gclid=Cj0KCQiA-aGCBhCwARIsAHDl5x_DlEYb0pYYtmgAr9cjXVF0QGmTTsS1GiKGXUoePvave7whzGWPw7UaAuEpEALw_wcB&hvadid=419844191966&hvdev=c&hvlocphy=9062530&hvnetw=g&hvqmt=b&hvrand=11603187779769807271&hvtargid=kwd-309863667&hydadcr=21310_51059&keywords=head+first+design+patterns&qid=1615381361&sr=8-2)

Chapter 1 - Create a Pizza
-
Let's say you are a pizza shop owner in a big city, to serve your customer a pizza this is how you do it

```java
    /*
        A very simple code to make a pizza and serve it to our lovely customer
    */
    public Pizza servePizza() {
        Pizza pizza = new Pizza();
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        
        return pizza;
    }
```

Chapter 2 - Need to add a new Menu
-
For a pizza shop owner in a big city, you can't only have one menu otherwise another pizza shop will beat you up.
You need to innovate your menu so the customer always coming to your store.

```java
    /*
        Another requirement came and we need to make another type of pizza, 
        how can we do that?
    */
    public Pizza servePizza(String type) { // 1. we pass the type of pizza that we want to make for our customer
        Pizza pizza;
        
        // 2. We check every combination of pizza that we serve in our store and create it
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        }
        // 3. Do preparation...
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        
        return pizza;
    }
```

Chapter 3 - Open Closed Principle Violation
-
Technically there's nothing wrong with our way but if we do it this way then whenever we have a new type of ingredients 
we need to always modify our class. In other words our code will not be "closed for modification" since to extend a new 
type we need to reopen it. To solve this problem we can encapsulate the algorithm to create the pizza into another class, 
and that class is what we call as a `Factory`.
```java

    /*
     * Solve the problem using Simple Factory Pattern
     * */
    
    public class SimplePizzaFactory {
        
        public Pizza createPizza(String type) {
            Pizza pizza = null;
            if (type.equals("cheese")) {
                pizza = new CheesePizza();
            } else if (type.equals("pepperoni")) {
                pizza = new PepperoniPizza();
            } else if (type.equals("clam")) {
                pizza = new ClamPizza();
            } else if (type.equals("veggie")) {
                pizza = new VeggiePiza();
            }
            return pizza;
        }
    }
    
    public class PizzaStore {
        SimplePizzaFactory factory;
        
        PizzaStore(SimplePizzaFactory factory) {
            this.factory = factory
        }

        public Pizza servePizza(String type) { 
            Pizza pizza;

            pizza = factory.createPizza(type) // replacing the "new SomeClass()"
                    
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }
    }
```

Chapter 4 - Franchising Pizza Store
-
Your pizza store had done well and now everyone wants a PizzaStore in their own neighborhood. 
You want to ensure the quality of the franchise operation and each franchise will offer different style of pizzas 
(New york, Chicago and California). To solve this problem we can create 3 different factories, 
NYPizzaFactory, ChicagoFactory and CaliforniaPizzaFactory.

```java
    public static void main(String[] args) {
        // Create a factory to serve NY Pizza Style
        NYPizzaFactory nyFactory = new NYPizzaFactory();
        PizzaStore nyStore = new PizzaStore(nyFactory);
        nyStore.servePizza("veggie")
        
        // Create a factory to serve Chicago Pizza Style
        ChicagoPizzaFactory chicagoFactory = new ChicagoPizzaFactory();
        PizzaStore chicagoStore = new PizzaStore(chicagoFactory);
        chicagoStore.servePizza("veggie")
    }
```
Chapter 5 - Quality Control
-
You found out that many each Pizza Store for each franchises were using the factory to create a pizza, 
but they were also starting to employ their own procedures for the rest of the process (example: forget to cut the pizza, 
bake things a little differently). Now you want to create a framework that ties pizza creation, 
and the process of all franchise. To Solve this problem we can use `abstract method` and then create Pizza Store 
subclass for each regional style. This Pattern called `Factory Method`

```java
    
    // 1. We make PizzaStore as an abstract classes
    public abstract class PizzaStore {

        public Pizza servePizza(String type) {
            Pizza pizza;

            pizza = createPizza(type) // replacing the "new SomeClass()"

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();

            return pizza;
        }
        
        // 2. Have an abstract method here
        abstract Pizza createPizza(String type)
    }
    
    // 3. Create Regional Pizza Store and extend from PizzaStore
    public class NYStylePizzaStore extend PizzaStore {
        
        // 4. Define the specific implementation on how NY Pizza Store create the Pizza
        @Override
        public Pizza createPizza(String type) {
            if (type.equals("cheese")) {
                return new NYStyleCheesePizza();
            } else if (type.equals("pepperoni")) {
                return new NYStylePepperoniPizza();
            } else if (type.equals("clam")) {
                return new NYStyleClamPizza();
            } else if (type.equals("veggie")) {
                return new NYStyleVeggiePiza();
            }
        }
    }

    public class ChicagoStylePizzaStore extend PizzaStore {

        // 5. Define the specific implementation on how Chicago Pizza Store create the Pizza
        @Override
        public Pizza createPizza(String type) {
            if (type.equals("cheese")) {
                return new ChicagoStyleCheesePizza();
            } else if (type.equals("pepperoni")) {
                return new ChicagoStylePepperoniPizza();
            } else if (type.equals("clam")) {
                return new ChicagoStyleClamPizza();
            } else if (type.equals("veggie")) {
                return new ChicagoStyleVeggiePiza();
            }
        }
    }

    public static void main(String[] args) {
        // Create a different store
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        
        //Serve Ny Style Pizza
        Pizza pizza = nyPizzaStore.servePizza("cheese");
        
        //Serve Chicago Style Pizza
        pizza = chicagoStore.servePizza("cheese")
    }
```

Chapter 6 - Consistency For Each Franchise
-
Your restaurant is really shaping up, and it's got a flexible framework but there're some franchises that 
substituting inferior ingredients in their pies to lower cost and increase their margins. 
To solve this you want to build a factory that produces and ship them to the franchises. 
The factory will deliver families of ingredient based on each regional for example New York, California, Seatle, etc.
To solve this problem we are going to use abstract factory.