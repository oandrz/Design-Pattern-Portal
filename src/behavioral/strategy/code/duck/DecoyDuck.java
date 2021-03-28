package behavioral.strategy.code.duck;

import behavioral.strategy.code.strategy.fly.FlyBehavior;
import behavioral.strategy.code.strategy.quack.QuackBehavior;

public class DecoyDuck extends Duck {
    public DecoyDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("Decoy Duck");
    }
}
