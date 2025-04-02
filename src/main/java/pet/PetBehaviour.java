package pet;
import user.UserProfile;
public class PetBehaviour {

    protected Pet pet;

    public PetBehaviour(Pet pet) {
        this.pet = pet;
    }

    public void tryToMove() {
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.move();  
        } else if (pet.getHealth().getStatus() == PetHealthStatus.SICK) {
            pet.hibernate();
            pet.sleep();
        } else {
            System.out.println("RIP");
        }
    }

    public void tryToEat() {
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.eat(); 
        } else if (pet.getHealth().getStatus() == PetHealthStatus.SICK) {
            System.out.println("Pet is not healthy enough to eat.");
        }
    }

    public void tryToPlay() {
        if (pet.getHealth().getStatus() == PetHealthStatus.HEALTHY) {
            pet.play(); 
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
