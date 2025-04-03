package pet;

public class PetHealth {
    private PetHealthStatus status;
    private PetBehaviour behaviour;

    public PetHealth(PetHealthStatus status) {
        this.status = status;
    }

    public PetHealthStatus getStatus() {
        return status;
    }

    public void setStatus(PetHealthStatus status) {
        this.status = status;
    }

    public void updateStatus(PetHealthStatus newStatus) {
        this.status = newStatus;
        System.out.println("Pet health status updated to: " + newStatus);
    }
}