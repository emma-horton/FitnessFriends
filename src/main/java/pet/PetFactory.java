package pet;
import pet.type.Parrot;
import pet.type.Turtle;

public class PetFactory {
    // Factory method to create a Pet (Dog) with health status
    public static PetBehaviour createPet(String type, int petId, PetHealthStatus health) {
        if (type == null) {
            throw new IllegalArgumentException("Pet type cannot be null");
        }
        PetHealth healthStatus = new PetHealth(health);
        switch (type.toLowerCase()) {
            case "parrot":
                Pet parrot = new Parrot(petId, healthStatus);
                PetBehaviour parrotBehaviour = new PetBehaviour(parrot);
                return parrotBehaviour;
            case "turtle":
                Pet turtle = new Turtle(petId, healthStatus);
                PetBehaviour turtleBehaviour = new PetBehaviour(turtle);
                return turtleBehaviour;
            default:
                throw new IllegalArgumentException("Unknown pet type: " + type);
        }
    }
}
