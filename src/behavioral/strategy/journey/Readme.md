# Strategy Journey, from zero to Abstract Factory

Reference: [From Head First Design Pattern, Page 38](https://www.amazon.sg/Head-First-Design-Patterns-Freeman/dp/0596007124/ref=sr_1_2?adgrpid=101389983568&dchild=1&gclid=Cj0KCQiA-aGCBhCwARIsAHDl5x_DlEYb0pYYtmgAr9cjXVF0QGmTTsS1GiKGXUoePvave7whzGWPw7UaAuEpEALw_wcB&hvadid=419844191966&hvdev=c&hvlocphy=9062530&hvnetw=g&hvqmt=b&hvrand=11603187779769807271&hvtargid=kwd-309863667&hydadcr=21310_51059&keywords=head+first+design+patterns&qid=1615381361&sr=8-2)

Chapter 1 - Duck Game Simulator
-
You are working at a company that makes duck pond simulation game. The game can show a large variety of duck species 
swimming and making quacking sound. The past owner create 1 duck super class to be inherited by other duck types.

```java
public abstract class Duck {
    
    public void quack() {
        
    }
    
    public void swim() {
        
    }

    // Each duck type have their own look
    public abstract void display() 
}

// Child of duck class
public class MallardDuck extends Duck {
    @Override
    public void display() {
        
    }
}

// Child of duck class
public class RedHeadDuck extends Duck {
    @Override
    public void display() {

    }
}
    
    // There are many more duck sub class other than these 2
```

Chapter 2 - Need the duck to fly
-
Another requirement came, and the Product Owner decided to implement fly feature for the duck. then you decided to 
just add another new method called `fly()` where you will put the algorithm for fly there.

```java
public abstract class Duck {

    public void quack() {

    }

    public void swim() {

    }
    
    // We add another new method for fly
    public void fly() {
        
    }

    public abstract void display()
}

// No Changes required
public class MallardDuck extends Duck {
    @Override
    public void display() {

    }
}

// No Changes required
public class RedHeadDuck extends Duck {
    @Override
    public void display() {

    }
}
    
    // There are many more duck sub class other than these 2
```

Chapter 3 - Rubber Duck
-
Your manager do the demo for the work that you've done. But your manager find there's 1 duck with un-proper behavior, 
the rubber duck. It's supposed not to fly, it's sounded squeak instead of quack. Your manager come to report his finding.  
After getting the report from your manager, you decided using inheritance may solve the problem, but it won't scale enough
for covering the other types duck. 
```java

public abstract class Duck {

    public void quack() {

    }

    public void swim() {

    }

    // We add another new method for fly
    public void fly() {

    }

    public abstract void display()
}

//....

// It was supposed to not fly and squeak
public class RubberDuck extends Duck {
    @Override
    public void display() {

    }
}

// What about this type of duck which supposed to not sound and fly
public class DecoyDuck extends Duck {
    @Override
    public void display() {

    }
}
```

Chapter 4 - Using Interface
-
After giving some thought you have an idea to use `interface` where you will extract the fly and quack behavior from 
the `Duck`. The factor that giving you this decision is because not every duck can fly nor quack, if using interface, 
the duck can just implement the behavior supported by the duck only. You present this solution to your manager, but 
he doesn't like the idea since you're doing this, there will be many duplicate code and if you need to make a little change
to the flying behavior you will need to change it throughout the entire duck class which is `not scallable solution`.

```java
public interface Fly {
    void fly()
}

public interface Quack {
    void quack()
}

// Remove the quack and fly implementation from the class
public abstract class Duck {
    public abstract void display()
}

// Each duck implement the behavior that it support
public class RedHeadDuck extends Duck implements FlyBehavior, QuackBehavior {
    @Override
    public void display() {

    }

    @Override
    public void fly() {
        // Do the fly process
    }

    @Override
    public void quack() {
        // Do the quack process
    }
}

// Rubber duck cannot fly so it will only implement the quack behavior
public class RubberDuck extends Duck implements QuackBehavior {
    @Override
    public void display() {

    }

    @Override
    public void quack() {
        // Do the quack process
    }
}

// Decoy duck mute and cannot fly, so it doesn't need to implement anything
public class DecoyDuck extends Duck {
    @Override
    public void display() {

    }
}
```
Chapter 5 - Separation of Concern
-
You're reviewing your solution so far. You learn from First solution when you're using inheritance not all duck can have the fly 
or quack behavior, and you will duplicate the entire algorithm when you use interface since you can not reuse the code 
that you already have. After doing some research you find a principle that says 
`identify the aspects of your application that vary and separate them from what stays the same`. 
This principle called `separation of concern`. 

[![duck.jpg](https://i.postimg.cc/xCLjbSVr/duck.jpg)](https://postimg.cc/G8hnZVpz)

Chapter 6 - Implement Fly Behavior and Quack Behavior
-
With the principle, you decided to `encapsulate the fly behavior and quack behavior from the duck class and to provide more flexibility`
when there's another behavior added in the future, you also decided to use a `common interface as an abstraction`. The interface will be
implemented by specific behavior class.
```java
// We create an separate interface as a common abstraction of our behavior or algorithm class
public interface FlyBehavior {
    void fly();
}

// We create the specific implementation of the abstraction
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        
    }
}

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {

    }
}

// We create an separate interface as a common abstraction of our behavior or algorithm class
public interface QuackBehavior {
    void quack();
}

// We create the specific implementation of the abstraction
public class Quack implements QuackBehavior {
    @Override
    public void quack() {

    }
}

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {

    }
}

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {

    }
}
```
Chapter 6 - Integration
-
You already finished create the behavior class for fly and quack, now it is time for the integration. To integrate the behavior,
The duck class that you've created before will delegate the action for fly or quack into another instance. This duck class called as a context object.
And now you're already finished implementing a strategy pattern.

```java
import behavioral.strategy.code.strategy.fly.FlyBehavior;
import behavioral.strategy.code.strategy.quack.QuackBehavior;

// Duck will become a context class where it will save the reference of the behavior it's going to use
public abstract class Duck {

    // create an instance to store the behavior
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;
    
    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    // Context object will delegate the action into the behavior class
    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    public abstract void display()
}

//....

public class RubberDuck extends Duck {

    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }
    
    @Override
    public void display() {

    }
}

public class DecoyDuck extends Duck {

    public DecoyDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }
    
    @Override
    public void display() {

    }
}

```