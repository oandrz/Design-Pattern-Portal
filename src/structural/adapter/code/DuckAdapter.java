package structural.adapter.code;

import structural.adapter.code.duck.Duck;
import structural.adapter.code.turkey.Turkey;

// Adater class, adapting Turkey Interface (Adaptee) into Duck Interface (Target)
public class DuckAdapter implements Duck  {

    private Turkey turkey;

    public DuckAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        turkey.fly();
    }
}
