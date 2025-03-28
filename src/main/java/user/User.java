package user;
import pet.PetBehaviour;


public class User {
    private int userId;
    private String username;
    private UserProfile profile;
    private PetBehaviour petBehaviour;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User(int userId, String username, UserProfile profile, PetBehaviour petBehaviour) {
        this.userId = userId;
        this.username = username;
        this.profile = profile;
        this.petBehaviour = petBehaviour;
    }

    // Getter for petBehaviour
    public PetBehaviour getPetBehaviour() {
        return petBehaviour;
    }

    // Optionally, you can add a setter if needed
    public void setPetBehaviour(PetBehaviour petBehaviour) {
        this.petBehaviour = petBehaviour;
    }
    
    public UserProfile getProfile() {
        return profile; // Getter for UserProfile
    }

}
