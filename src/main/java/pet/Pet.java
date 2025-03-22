package pet;

public abstract class Pet extends PetBehaviour {
    private int petId;
    private PetHealth health;

    public Pet(int petId, PetHealth health) {
        super(null);
        this.petId = petId;
        this.health = health;
    }
    public int getPetId() {
        return petId;
    }
    public PetHealth getHealth() {
        return health;
    }
    public abstract void eat();
    public abstract void play();
    public abstract void move();
    public abstract void sleep();
    public abstract void hibernate();


}