package vehicles;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = PriceValidator.class)
public @interface Price {

    String message() default  "The given price tag is not correct. It must be larger than or equal than 100.000( one hundred thousand) and it has to be rounded to the nearest hundred thousand";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payloads() default {};
    int minValue() default 100000;
}
