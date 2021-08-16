package vehicles;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(Long id) {
        super("Vehicle not found with id: " + id);
    }
}
