package vehicles;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(Long id) {
        super("Vehicle os sensitive data not found with id: " + id);
    }
}
