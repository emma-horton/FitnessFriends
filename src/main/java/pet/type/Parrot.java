package pet.type;
import pet.Pet;
import pet.PetHealth;

public class Parrot extends Pet {

    public Parrot(int petId, PetHealth health) {
        super(petId, health);
    }
    public void eat() {
        System.out.println("Crunch! Crunch! Tasty Seeds");
    }
    public void play() {
        System.out.println("Squark! Squark! Lets play walk the plank!");
    }
    public void move() {
        System.out.println("Swooshing through the air");
        System.out.println("Aye Aye Captain! I'm a pirate parrot. Welcome on deck!");
    }
    public void hibernate() {
        System.out.println("Hiding in cage, I want to come out");
    }
    public void sleep() {
        System.out.println("Snoozing, Snoozing, Drooling");
    }
}
