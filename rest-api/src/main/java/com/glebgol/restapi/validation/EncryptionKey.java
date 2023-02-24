package com.glebgol.restapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EncryptionKeyValidator.class)
@Documented
public @interface EncryptionKey {
    String message() default "{EncryptionKey.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
