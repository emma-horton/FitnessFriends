package pet.type;

import pet.Pet;
import pet.PetHealth;

public class Parrot extends Pet {
    public Parrot(int petId, PetHealth health, String name) {
        super(petId, health, name);
    }

    @Override
    protected void performMove() {
        System.out.println("Swooshing through the air!");
    }

    @Override
    protected void performEat() {
        System.out.println("Crunch! Crunch! Tasty seeds.");
    }

    @Override
    public void play() {
        System.out.println("Squawk! Squawk! Let's play walk the plank!");
    }

    @Override
    public void sleep() {
        System.out.println("Snoozing, Snoozing, Drooling.");
    }

    @Override
    public void hibernate() {
        System.out.println("Hiding in cage, I want to come out.");
    }
}