package vehicles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator <Price, Integer>{
    private int minValue;

    @Override
    public void initialize(Price constraintAnnotation) {
        minValue = constraintAnnotation.minValue();
    }

    @Override
    public boolean isValid(Integer priceTag, ConstraintValidatorContext constraintValidatorContext) {
        return priceTag >minValue &&
                priceTag % 10000 == 0;
    }
}
