package behavioral.strategy.code.duck;

import behavioral.strategy.code.strategy.fly.FlyBehavior;
import behavioral.strategy.code.strategy.quack.QuackBehavior;

public class RubberDuck extends Duck {
    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("Rubber Duck");
    }
}
