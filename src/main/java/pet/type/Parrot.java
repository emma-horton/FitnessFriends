package pet.type;
import pet.Pet;
import pet.PetHealth;

public class Parrot extends Pet {

    public Parrot(int petId, PetHealth health, String name) {
        super(petId, health, name);
    }
    public void eat() {
        System.out.println("Crunch! Crunch! Tasty Seeds");
    }
    public void play() {
        System.out.println("Squark! Squark! Lets play walk the plank!");
    }
    public void move() {
        System.out.println("Arggh! Me names is " + getName());
        System.out.println("Swooshing through the air");
    }
    public void hibernate() {
        System.out.println("Hiding in cage, I want to come out");
    }
    public void sleep() {
        System.out.println("Snoozing, Snoozing, Drooling");
    }
}
