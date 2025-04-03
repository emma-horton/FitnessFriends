package pet.state;
import pet.Pet;

public class HealthyState implements PetState {
    @Override
    public void move(Pet pet) {
        pet.move();
    }

    @Override
    public void eat(Pet pet) {
        pet.eat();
    }

    @Override
    public void play(Pet pet) {
        pet.play();
    }
}