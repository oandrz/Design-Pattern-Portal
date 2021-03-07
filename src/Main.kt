import creational.abstractfactory.main.factory.ChicagoPizzaIngredientFactory
import creational.abstractfactory.main.factory.NewYorkPizzaIngredientFactory
import creational.abstractfactory.main.factory.PizzaIngredientFactory

fun main() {
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