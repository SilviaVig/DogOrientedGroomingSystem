package org.woofenterprise.dogs.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import javax.validation.Constraint;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {DateBeforeValidator.class} )
@Documented

public @interface DateBefore {
    String message() default "must be before {value}";
    String pattern() default "yyyy-MM-dd";
    String value();
    Class[] groups() default {};
    Class[] payload() default {};       
}