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

    
    // Template method for moving
    public void move() {
        System.out.println(getName() + " is preparing to move...");
        performMove(); // Primitive operation
        System.out.println(getName() + " has finished moving.");
    }

    // Template method for eating
    public void eat() {
        System.out.println(getName() + " is preparing to eat...");
        performEat(); // Primitive operation
        System.out.println(getName() + " has finished eating.");
    }

    // Abstract methods (primitive operations)
    protected abstract void performMove();
    protected abstract void performEat();

    public abstract void play();
    public abstract void sleep();
    public abstract void hibernate();

}