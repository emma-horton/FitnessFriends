package pet.state;
import pet.Pet;

public class SickState implements PetState {
    @Override
    public void move(Pet pet) {
        pet.hibernate();
        pet.sleep();
    }

    @Override
    public void eat(Pet pet) {
        System.out.println("Pet is not healthy enough to eat.");
    }

    @Override
    public void play(Pet pet) {
        System.out.println("Pet is not healthy enough to play.");
    }
}