package pet;

public abstract class Pet extends PetBehaviour {
    private int petId;
    private PetHealth health;
    private String name;

    public Pet(int petId, PetHealth health, String name) {
        super(null);
        this.petId = petId;
        this.health = health;
        this.name = name;
        super.setPet(this); // Set the pet reference after initialization
        updateState(); // Call updateState() after the pet is fully initialized
    }
    public int getPetId() {
        return petId;
    }
    public PetHealth getHealth() {
        return health;
    }

    public String getName() {
        if (name == null || name.isEmpty()) {
            return name; 
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1); 
    }

    
    public abstract void eat();
    public abstract void play();
    public abstract void move();
    public abstract void sleep();
    public abstract void hibernate();


}