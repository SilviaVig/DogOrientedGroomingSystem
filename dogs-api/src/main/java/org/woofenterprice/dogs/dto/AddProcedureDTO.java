package org.woofenterprice.dogs.dto;

import java.util.Objects;
import org.woofenterprise.dogs.utils.Procedure;

/**
 * DTO for Dog entity.
 * @author michal.babel@embedit.cz
 */
public class AddProcedureDTO {
    private Long appointmentId;
    private Procedure procedure;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.appointmentId);
        hash = 71 * hash + Objects.hashCode(this.procedure);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddProcedureDTO other = (AddProcedureDTO) obj;
        if (!Objects.equals(this.appointmentId, other.appointmentId)) {
            return false;
        }
        return this.procedure == other.procedure;
    }
    
    
}
