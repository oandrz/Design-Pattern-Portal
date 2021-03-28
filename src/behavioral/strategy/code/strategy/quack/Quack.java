package behavioral.strategy.code.strategy.quack;

// We implement the specific behavior for quack behavior
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        // We implement the quack behavior
        System.out.println("Quack Quack");
    }
}
