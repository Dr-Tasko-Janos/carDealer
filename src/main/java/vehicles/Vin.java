package vehicles;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = NameValidator.class)
public @interface Vin {

    String message() default "he VIN is a 17-character string of letters and numbers without intervening spaces or the letters Q (q), I (i), and O (o); these are omitted to avoid confusion with the numerals 0 and 1.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payloads() default {};
    int requiredLength() default 17;
}