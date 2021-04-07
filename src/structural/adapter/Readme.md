# Structural Pattern 1 - Adapter

Intent of the Design Pattern
-
From `Design Pattern: Element Reusable` by Gang Of Four
> Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.

How do we implement the Pattern? [Complete Diagram](https://drive.google.com/file/d/1H3rfODfepOWlBJpV_jduV2WSCFA1yfgf/view?usp=sharing)
- 
- Make sure to have 2 classes with incompatible interfaces.
  
  [![image_adapter_1](https://i.postimg.cc/QxVjLHdz/Screenshot-2021-04-07-at-8-23-11-PM.png)](https://postimg.cc/svFk7fSP)
- Create an Adapter Class, then have it extend the target interface/object.
  
  [![Screenshot-2021-04-07-at-8-28-44-PM.png](https://i.postimg.cc/hvcbJkfJ/Screenshot-2021-04-07-at-8-28-44-PM.png)](https://postimg.cc/qhDCSZc0)
- Have the adapter store reference of the adaptee (an object that need adapting) that being passed through the adapter constructor.
- Delegate any action of the target object/interface to the adaptee's action.
  [![Screenshot-2021-04-07-at-8-43-12-PM.png](https://i.postimg.cc/KzPLP0Md/Screenshot-2021-04-07-at-8-43-12-PM.png)](https://postimg.cc/k65B9Fwc)

When do we use the Design Pattern?
--
- When you want to use an existing class, and it interfaces does not match the one you need
- When you want to create a reusable class that cooperates with class that don't necessarily have compatible interface.

Structure
- 
[![Screenshot-2021-04-07-at-8-40-45-PM.png](https://i.postimg.cc/gcL1W8P1/Screenshot-2021-04-07-at-8-40-45-PM.png)](https://postimg.cc/NLYd8yFx)

Pros and Cons
-
Pros | Cons
--- | ---
The amount of the adapter does depends on how similar we want adaptee to be to the target interface | Makes hard to override adaptee behavior, since it will require subclassing the adaptee and making adapter store the reference of the subclass.
A class will be more reusable since we eliminate the assumption that other classes see the same interface, since you need to create the adapter to convert it into target class | Adapted object no longer conform to the adaptee interface, so it can't be used as the normal adaptee object
 | 

Example Case Problem
-  
Let's say you're travelling to Great Britain. You want to charge your laptop that has US Standard Plug. 
How will you be able to plug into the Britain outlet ? You have 2 classes `UsElectricityPlug` and `BritainElectricityOutlet`
```java
class UsElectricityPlug {
    void plug() {
        // Do Plug
    }
}

class BritainElectricityOutlet {
    void plugged() {
        // Getting Plugged
    }
}
```
Solution of the Case Problem
- 
- Create a new class called `BritainElectricityOutletAdapter`, this class will become an adapter class and have 
  it extend from the target class `BritainElectricityOutlet`
```java
class BritainElectricityOutletAdapter extends BritainElectricityOutlet {
        
}
```
- Make the class store the reference of the adaptee (UsElectricityPlug) and pass the instance of the
  adaptee through the adapter constructor.
```java
class BritainElectricityOutletAdapter extends BritainElectricityOutlet {

    private UsElectricityPlug plug;
    
    public BritainElectricityOutletAdapter(UsElectricityPlug plug) {
        this.plug = plug
    }
}
```
- Override the behavior of the target interface and delegate the task into the adaptee
```java
class BritainElectricityOutletAdapter extends BritainElectricityOutlet {

    private UsElectricityPlug electricityPlug;

    public BritainElectricityOutletAdapter(UsElectricityPlug plug) {
        electricityPlug = plug
    }
    
    @Override
    public void plugged() {
        electricityPlug.plug()
    }
}
```
Implementation Technique
--

