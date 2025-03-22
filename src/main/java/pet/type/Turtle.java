package pet.type;
import pet.Pet;
import pet.PetHealth;
public class Turtle extends Pet {
    public Turtle(int petId, PetHealth health) {
        super(petId, health);
    }
    public void eat() {
        System.out.println("Munch! Munch! Tasty Salad Treat");
    }
    public void play() {
        System.out.println("Wiggle Wiggle. Lets play catch!");
    }
    public void move() {
        System.out.println("Swoodh! Swoodh! Swimming in the water");
    }
    public void sleep() {
        System.out.println("Snoozing and Snoring in my bowl");
    }
    public void hibernate() {
        System.out.println("Retracting my head and legs into my shell");
        System.out.println("Very Verryyy sleepy. I'm going to hibernate in my shell for a while");
    }
}
