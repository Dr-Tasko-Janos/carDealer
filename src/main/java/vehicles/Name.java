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
public @interface Name {

    String message() default "The length of the FirstName/LastName must be greater than 2 characters, and must be less than 50 characters (2-50 characters) and the first character must be capital letter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payloads() default {};

    int maxLength() default 50;
}
