package behavioral.strategy.code.duck;

import behavioral.strategy.code.strategy.fly.FlyBehavior;
import behavioral.strategy.code.strategy.quack.QuackBehavior;

/*
* We create a context class called Duck. it will maintain the reference to a strategy object, but it doesn't have the
  privilege to decides which strategy that is going to use.
* */
public abstract class Duck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    // We can set the behavior of the duck once it's created
    public Duck(
        FlyBehavior flyBehavior,
        QuackBehavior quackBehavior
    ) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    // We create the method for the client to set the flying behavior of the duck
    public void setFlyBehavior(FlyBehavior behavior) {
        flyBehavior = behavior;
    }

    // We create the method for the client to set the quack behavior of the duck
    public void setQuackBehavior(QuackBehavior behavior) {
        quackBehavior = behavior;
    }

    public abstract void display();
}
