package behavioral.strategy.code.strategy.fly;

// We implement the specific behavior from fly behavior
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        // We won't do anything since this behavior specifically created if the duck can't fly
        System.out.println("Can't Fly");
    }
}
