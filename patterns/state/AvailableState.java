// файл: /patterns/state/AvailableState.java
package patterns.state;

public class AvailableState implements CarState {
    public void handleState() {
        System.out.println("Car is available.");
    }
}
