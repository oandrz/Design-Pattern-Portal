package creational.abstractfactory.test

import creational.abstractfactory.main.factory.ChicagoPizzaIngredientFactory
import creational.abstractfactory.main.factory.NewYorkPizzaIngredientFactory
import creational.abstractfactory.main.factory.PizzaIngredientFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ChicagoPizzaFactoryTest {

    private val factory: PizzaIngredientFactory = ChicagoPizzaIngredientFactory()

    @BeforeEach
    fun setUp() {
        assertTrue { factory.type == "Chicago" }
    }

    @Test
    fun testCreateDough_givenDough_whenGetType_shouldReturnThickCrushDough() {
        assertTrue { factory.createDough().type == "Thin Crust" }
    }

    @Test
    fun testCreateSauce_givenSauce_whenGetType_shouldReturnMarinaraSauce() {
        assertTrue { factory.createSauce().type == "Plum Tomato Sauce" }
    }

    @Test
    fun testCreateCheese_givenCheese_whenGetType_shouldReturnMozzarellaCheese() {
        assertTrue { factory.createCheese().type == "Reggiano Cheese" }
    }
}