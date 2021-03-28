package behavioral.strategy.code.strategy.quack;

// We implement the specific behavior for quack behavior
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        // We won't do anything since the specific behavior can't sound
        System.out.println("silent.....");
    }
}
