package pet;
import pet.state.PetState;
import pet.state.HealthyState;
import pet.state.SickState;
import user.UserProfile;
import pet.state.DeadState;

public class PetBehaviour {
    private Pet pet;
    private PetState state;

    public PetBehaviour(Pet pet) {
        this.pet = pet;
        if (pet != null) {
            updateState(); // Initialize the state only if the pet is not null
        }
    }

    public void updateState() {
        switch (pet.getHealth().getStatus()) {
            case HEALTHY:
                state = new HealthyState();
                break;
            case SICK:
                state = new SickState();
                break;
            case DEAD:
                state = new DeadState();
                break;
        }
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
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

    public void tryToMove() {
        state.move(pet);
    }

    public void tryToEat() {
        state.eat(pet);
    }

    public void tryToPlay() {
        state.play(pet);
    }
}