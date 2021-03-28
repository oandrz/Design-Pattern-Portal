package behavioral.strategy.code.duck;

import behavioral.strategy.code.strategy.fly.FlyBehavior;
import behavioral.strategy.code.strategy.quack.QuackBehavior;

public class RedheadDuck extends Duck {
    public RedheadDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("Red Head Duck");
    }
}
