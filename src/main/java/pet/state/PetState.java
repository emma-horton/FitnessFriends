package pet.state;

import pet.Pet;

public interface PetState {
    void move(Pet pet);
    void eat(Pet pet);
    void play(Pet pet);
}