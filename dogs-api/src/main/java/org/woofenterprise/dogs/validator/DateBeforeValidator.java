/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author michal
 */
class DateBeforeValidator implements ConstraintValidator<DateBefore, Date> {

    private Date upperBound;
    
    @Override
    public void initialize(DateBefore a) {
        DateFormat dateFormat = new SimpleDateFormat(a.pattern());
        try {
            upperBound = dateFormat.parse(a.value());
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isValid(Date t, ConstraintValidatorContext cvc) {
        return t == null || t.before(upperBound);
    }
    
}
