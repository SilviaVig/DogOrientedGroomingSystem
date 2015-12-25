package org.woofenterprise.dogs.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import javax.validation.Constraint;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {DateRangeValidator.class} )
@Documented

public @interface ValidateDateRange {
    String message() default "endTime should be after startTime";
    Class[] groups() default {};
    Class[] payload() default {};       
}