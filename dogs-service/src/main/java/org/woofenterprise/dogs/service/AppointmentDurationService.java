package org.woofenterprise.dogs.service;

import java.util.Collection;
import org.woofenterprise.dogs.utils.Procedure;

/**
 * @author Silvia.Vigasova
 */
public interface AppointmentDurationService {
    /**
     * Returns the duration of an appointment in minutes. 
     * The duration depends on the specific procedures. And also the duration of procedures depends on other procedures.
     * WASHING depends on BRUSHING and FLEAS_REMOVING
     * 
     * @param procedures collection of procedures
     * @return duration for appointment in unit 
     */
    long getDurationForProcedures(Collection<Procedure> procedures);
}
