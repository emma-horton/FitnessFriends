package pet;
import pet.PetHealthStatus;
import pet.PetHealth;
public class PetHealth {
    private PetHealthStatus status;

    public PetHealth(PetHealthStatus status) {
        this.status = status;
    }

    public PetHealthStatus getStatus() {
        return status;
    }

    public void setStatus(PetHealthStatus status) {
        this.status = status;
    }

}
