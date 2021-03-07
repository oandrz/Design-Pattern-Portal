package creational.abstractfactory.test

import creational.abstractfactory.main.factory.NewYorkPizzaIngredientFactory
import creational.abstractfactory.main.factory.PizzaIngredientFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class NewYorkPizzaFactoryTest {

    private val factory: PizzaIngredientFactory = NewYorkPizzaIngredientFactory()

    @Test
    fun testCreateDough_givenDough_whenGetType_shouldReturnThickCrushDough() {
        assertTrue { factory.type == "New York Style" }
        assertTrue { factory.createDough().type == "Thick Crust" }
    }

    @Test
    fun testCreateSauce_givenSauce_whenGetType_shouldReturnMarinaraSauce() {
        assertTrue { factory.type == "New York Style" }
        assertTrue { factory.createSauce().type == "Marinara Sauce" }
    }

    @Test
    fun testCreateCheese_givenCheese_whenGetType_shouldReturnMozzarellaCheese() {
        assertTrue { factory.type == "New York Style" }
        assertTrue { factory.createCheese().type == "Mozarella Cheese" }
    }
}