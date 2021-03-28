package behavioral.strategy.code.strategy.fly;

// We implement the specific behavior for Fly Behavior
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        // implement the fly logic here
        System.out.println("Flying");
    }
}
