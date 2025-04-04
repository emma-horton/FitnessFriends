package pet.type;

import pet.Pet;
import pet.PetHealth;

public class Turtle extends Pet {
    public Turtle(int petId, PetHealth health, String name) {
        super(petId, health, name);
    }

    @Override
    protected void performMove() {
        System.out.println("Swoodh! Swoodh! Swimming in the water.");
    }

    @Override
    protected void performEat() {
        System.out.println("Munch! Munch! Tasty salad treat.");
    }

    @Override
    public void play() {
        System.out.println("Wiggle Wiggle. Let's play catch!");
    }

    @Override
    public void sleep() {
        System.out.println("Snoozing and snoring in my bowl.");
    }

    @Override
    public void hibernate() {
        System.out.println("Retracting my head and legs into my shell.");
        System.out.println("Very sleepy. I'm going to hibernate in my shell for a while.");
    }
}