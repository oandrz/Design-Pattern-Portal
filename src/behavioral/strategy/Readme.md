# Behavioral Pattern 1 - Strategy

Intent of the Design Pattern
-
From `Design Pattern: Element Reusable` by Gang Of Four
> Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently of a client that use it

How do we implement the Pattern? [Complete Diagram]()
- 
- Extract a class that does something specific in a lot of way (usually indicated by switch case or multiple branch if else usage).

  ```java
    public void buttonPress(String buttonType) {
        if (buttonType == DIRECTION_UP) { // algorithm to move up
            moveUp();
        } else if (buttonType == DIRECTION_DOWN) { // algorithm to move down
            moveDown();
        } else if (buttonType == DIRECTION_LEFT) { // algorithm to move left
            moveLeft();
        } else if (buttonType == DIRECTION_RIGHT) { // algorithm to move right
            moveRight();
        }
        // And many more, if there's another button we'll also need to 
        // add another condition for that new button
    } 
  ``` 
-  Extract the algorithm into a separate class called `Strategies`
   [![Screenshot-2021-03-15-at-8-39-05-PM.png](https://i.postimg.cc/vZccTqWk/Screenshot-2021-03-15-at-8-39-05-PM.png)](https://postimg.cc/75rxQNtn)

When do we use the Design Pattern?
-


Structure
- 

Pros and Cons
-

Example Case Problem
-  
  
Solution of the Case Problem
- 
