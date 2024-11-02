// файл: /patterns/state/SoldState.java
package patterns.state;

public class SoldState implements CarState {
    public void handleState() {
        System.out.println("Car is sold.");
    }
}
