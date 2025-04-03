package pet.state;
import pet.Pet;

public class DeadState implements PetState {
    @Override
    public void move(Pet pet) {
        System.out.println("RIP");
    }

    @Override
    public void eat(Pet pet) {
        System.out.println("RIP");
    }

    @Override
    public void play(Pet pet) {
        System.out.println("RIP");
    }
}