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


}
