package vehicles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VinValidator implements ConstraintValidator<Vin, String> {

    private int requiredLength;

    @Override
    public void initialize(Vin constraintAnnotation) {
        requiredLength = constraintAnnotation.requiredLength();
    }

    @Override
    public boolean isValid(String vin, ConstraintValidatorContext constraintValidatorContext) {
        return vin != null &&
                vin.matches("^[a-zA-Z0-9]*$") &&
                !vin.contains("Q") &&
                !vin.contains("I") &&
                !vin.contains("O");
    }
}