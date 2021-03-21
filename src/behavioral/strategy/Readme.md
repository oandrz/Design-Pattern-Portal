# Behavioral Pattern 1 - Strategy

Intent of the Design Pattern
--
From `Design Pattern: Element Reusable` by Gang Of Four
> Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently of a client that use it

How do we implement the Pattern? [Complete Diagram](https://drive.google.com/file/d/1znu89M-0phj5u8C7fIxHwx0S6GhBWrPZ/view?usp=sharing)
--
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
-  Extract the algorithm into a separate class called `Strategies`. From the example we see that each direction has their own algorithm so we can extract those algorithm into a separate Strategy class.
   

   [![Screenshot-2021-03-15-at-8-39-05-PM.png](https://i.postimg.cc/vZccTqWk/Screenshot-2021-03-15-at-8-39-05-PM.png)](https://postimg.cc/75rxQNtn)


- Create a context class. Context class will maintain the reference to a strategy object, but it doesn't have the 
  privilege to decides which strategy that is going to use. Client will set the strategy and context class will delegate the action into the strategy instance. We create a `Player` object 
  where it will become our context class.


  [![Screenshot-2021-03-21-at-9-21-32-AM.png](https://i.postimg.cc/7YgDpqRn/Screenshot-2021-03-21-at-9-21-32-AM.png)](https://postimg.cc/cr4V3G0v)

When do we use the Design Pattern? 
--
- When you have many behaviors in one class which appear as multiple conditional statement (usages of if else statement multiple time).
- When you have multiple version of algorithm, for example you want to implement different variants of sorting algorithm in your class.
You want the system to change the sorting algorithm easily.
- You want to avoid exposing complex, algorithm-specific data structures from the client.


Structure
--
[![Screenshot-2021-03-21-at-9-48-56-AM.png](https://i.postimg.cc/jdrCW30v/Screenshot-2021-03-21-at-9-48-56-AM.png)](https://postimg.cc/DmB7Hgzb)
- Strategy: Common interface for all supported algorithm.
- Concrete Strategy: The detail implementation of the algorithm
- Context: a class to maintain the reference to a strategy object and delegate the required action to it.

Pros and Cons
--
Pros | Cons
--- | ---
Re-usable family of algorithm or behaviors | Because concrete strategies share the same common interface, there's a chance that the concrete strategy won't use all the information passed to them through the interfaces. To solve this problem you tighten the dependencies between the strategy and the context (inject dependencies).   
Vary the algorithm independently, making it flexible, easier to switch and extend | Increase the number of the objects in an application. To solve this you can make the strategy as a stateless object (the concrete strategies won't remember the state) to be shared by the context and any state will be maintained by the context. 
Eliminate conditional statements for selecting desired behavior |

Example Case Problem
--
You want to build an apps that can simulate how sorting algorithm works. The apps will show a screen with a menu where the value of each menu is the name of the sorting algorithm.
If the user click one of the menu, it will navigate into simulation page showing the sorting simulation. You already created the apps to show 3 basic sorting algorithm for the first version: Bubble, Insertion and Selection.
You write your code like this:
```java
public void showSortingSimulatorPage(type: String) {
    if (type == "bubble") {
        simulateBubbleSort();
    } else if (type == "selection") {
        simulateSelectionSort();
    } else if (type == "insertion") {
        simulateInsertionSort();    
    }
}
```
Then you plan to refactor your code because you see that if you added the algorithm in just one class then the class will become too big and hard to maintain. 
What do you do to refactor this class so that you can extend another sorting algorithm easily without putting big chunk of code into the main class?
  
Solution of the Case Problem
--
- Based on the conditional statement that we already have, we will separate it into different classes. First we create a common interface for the strategy and create a separate strategies class based on the behaviors that we have.
```java
// Common Interface for the Specific Strategy
interface SortingStrategy {
    void sort(int[] arr);
}

// Specific Algorithm for the Strategy 
class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] arr) {
        // Do the sorting algorithm
    }
}

// Specific Algorithm for the Strategy
class SelectionSort implements SortingStrategy {
  @Override
  public void sort(int[] arr) {
    // Do the sorting algorithm
  }
}

// Specific Algorithm for the Strategy
class InsertionSort implements SortingStrategy {
  @Override
  public void sort(int[] arr) {
    // Do the sorting algorithm
  }
}
```

- Create the context class which will maintain the reference of the strategy. 
```java
class SortingSelector {
    
    private SortingStrategy sortingStrategy;
    
    public SortingSelector(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }
    
    public void execute(int[] arr) {
        sortingStrategy.sort(arr);
    }
}
```

- To use it in the client apps, we just need to do this.
```java
public static void main(String[] args) {
    SortingSelector selector = new SortingSelector(new BubbleSort());
    selector.execute(arr);
}
```

Implementation Technique
--
- Defining s