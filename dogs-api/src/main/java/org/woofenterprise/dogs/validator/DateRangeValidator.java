package org.woofenterprise.dogs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.woofenterprise.dogs.dto.AppointmentDTO;

/**
 *
 * @author michal
 */
class DateRangeValidator implements ConstraintValidator<ValidateDateRange, AppointmentDTO> {

    @Override
    public void initialize(ValidateDateRange a) {
        // nothing
    }

    @Override
    public boolean isValid(AppointmentDTO t, ConstraintValidatorContext cvc) {
        if (t.getStartTime() == null || t.getEndTime() == null) {
            return false;
        }
        
        return t.getStartTime().before(t.getEndTime());
    }
    
}
