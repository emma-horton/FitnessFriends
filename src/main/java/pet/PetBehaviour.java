package pet;
import pet.type.Turtle;
import user.UserProfile;
import pet.type.Parrot;
import pet.PetHealth;
public class PetBehaviour {

    protected Pet pet;

    public PetBehaviour(Pet pet) {
        this.pet = pet;
    }

    public void tryToMove() {
        // Check if pet is healthy
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.move();  // Call the move method if healthy
        } else if (pet.getHealth().getStatus() == PetHealthStatus.SICK) {
            pet.hibernate();
            pet.sleep();
        } else {
            System.out.println("RIP");
        }
    }

    public void tryToEat() {
        // Check if pet is healthy
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.eat();  // Call the eat method if healthy
        } else if (pet.getHealth().getStatus() == PetHealthStatus.SICK) {
            System.out.println("Pet is not healthy enough to eat.");
        }
    }

    public void tryToPlay() {
        // Check if pet is healthy
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.play();  // Call the play method if healthy
        } else if (pet.getHealth().getStatus() == PetHealthStatus.SICK){
            System.out.println("Pet is not healthy enough to play.");
        }
    }
    public void updateHealth(UserProfile profile) {
        if (profile.areAllGoalsAchievedThisWeek()) {
            pet.getHealth().updateStatus(PetHealthStatus.HEALTHY);
            System.out.println("Your pet is healthy and happy!");
        } else if (profile.areSomeGoalsAchievedThisWeek()) {
            pet.getHealth().updateStatus(PetHealthStatus.SICK);
            System.out.println("Your pet is feeling a bit sick. Complete more goals to improve its health!");
        } else if (profile.areAllGoalsMissedForTwoWeeks()) {
            pet.getHealth().updateStatus(PetHealthStatus.DEAD);
            System.out.println("Your pet has passed away due to neglect. Please take better care next time.");
        }
    }

    public Pet getPet() {
        return pet;
    }
}
