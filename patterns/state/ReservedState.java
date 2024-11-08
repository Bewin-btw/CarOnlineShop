package patterns.state;

public class ReservedState implements CarState {
    public void handleState() {
        System.out.println("Car is reserved.");
    }
}