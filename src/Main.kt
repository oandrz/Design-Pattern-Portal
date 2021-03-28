import behavioral.strategy.code.duck.MallardDuck
import behavioral.strategy.code.strategy.fly.FlyNoWay
import behavioral.strategy.code.strategy.fly.FlyWithWings
import behavioral.strategy.code.strategy.quack.Quack
import creational.abstractfactory.main.factory.ChicagoPizzaIngredientFactory
import creational.abstractfactory.main.factory.NewYorkPizzaIngredientFactory
import creational.abstractfactory.main.factory.PizzaIngredientFactory

fun main() {
    factoryExample()
    println()
    strategyExample()
}

private fun factoryExample() {
    var factory: PizzaIngredientFactory = NewYorkPizzaIngredientFactory()
    print("""
        ${factory.type} Pizza with Ingredients:         
        - ${factory.createDough().type}       
        - ${factory.createCheese().type}        
        - ${factory.createSauce().type}
    """.trimIndent())

    factory = ChicagoPizzaIngredientFactory()
    print("""
        
        ${factory.type} Pizza with Ingredients:         
        - ${factory.createDough().type}       
        - ${factory.createCheese().type}        
        - ${factory.createSauce().type}
    """.trimIndent())
}

private fun strategyExample() {
    // First we will create a mallard duck that can fly and making a quack sound just like a normal duck
    val duck = MallardDuck(FlyWithWings(), Quack())

    // If we want to change the behavior of the duck during run time, we can just set up the new behavior directly
    duck.setFlyBehavior(FlyNoWay())

    duck.run {
        display()
        performFly()
        performQuack()
    }
}