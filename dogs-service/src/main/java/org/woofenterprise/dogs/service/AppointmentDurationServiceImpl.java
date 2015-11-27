package org.woofenterprise.dogs.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.utils.Procedure;

import java.util.List;

import static org.woofenterprise.dogs.utils.Procedure.*;

/**
 * @author Silvia.Vigasova
 */
@Service
public class AppointmentDurationServiceImpl implements AppointmentDurationService {
    @Override
    public long getDurationForProcedures(Collection<Procedure> procedures) {
        long result = 0;
        for (Procedure procedure : procedures) {
            result += getTimeForProcedure(procedure);
        }
        result += getTimeDeltaForDependentProcedures(procedures);
        return result;
    }

    private long getTimeForProcedure(Procedure procedure) {
        switch (procedure) {
            case BRUSHING:
                return 20;
            case WASHING:
                return 45;
            case FLEAS_REMOVING:
                return 5;
            case TEETH_BRUSHING:
                return 7;
            case CLAWS_CUTTING:
                return 10;
            default:
                throw new IllegalArgumentException("This procedure is not supported.");
        }
    }

    private long getTimeDeltaForDependentProcedures(Collection<Procedure> procedures) {
        long delta = 0;
        if (procedures.contains(BRUSHING) && procedures.contains(WASHING)) {
            delta -= 10;
        }
        if (procedures.contains(FLEAS_REMOVING) && procedures.contains(WASHING)) {
            delta += 30;
        }
        if (procedures.contains(CLAWS_CUTTING) && procedures.contains(WASHING)) {
            delta -= 5;
        }
        return delta;
    }
}
