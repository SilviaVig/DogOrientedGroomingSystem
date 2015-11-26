package org.woofenterprise.dogs.service;

import org.junit.Test;
import org.woofenterprise.dogs.utils.Procedure;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Silvia.Vigasova
 */
public class AppointmentDurationServiceTest {
    
    AppointmentDurationService durationService = new AppointmentDurationServiceImpl();

    @Test
    public void testDurationNoProcedures() {
        List<Procedure> procedures = new ArrayList<>();
        long result = durationService.getDurationForProcedures(procedures);
        assertEquals(0, result);
    }

    @Test
    public void testDurationForProceduresNoDelta() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.BRUSHING);//20min
        procedures.add(Procedure.CLAWS_CUTTING);//10 min
        procedures.add(Procedure.TEETH_BRUSHING);//7 min
        procedures.add(Procedure.FLEAS_REMOVING);//5 min
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(42, result);
    }
    
    @Test
    public void testDurationForProceduresDelta() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.BRUSHING);//20min
        procedures.add(Procedure.CLAWS_CUTTING);//10 min
        procedures.add(Procedure.TEETH_BRUSHING);//7 min
        procedures.add(Procedure.FLEAS_REMOVING);//5 min
        procedures.add(Procedure.WASHING);//45min
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(102, result);
    }

    @Test
    public void testDurationForWashing() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.WASHING);//45
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(45, result);
    }

    @Test
    public void testDurationForClawsCutting() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.CLAWS_CUTTING);//10
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(10, result);
    }

    @Test
    public void testDurationForFleasRemoving() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.FLEAS_REMOVING);//5
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(5, result);

    }

    @Test
    public void testDurationForTeethBrushing() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.TEETH_BRUSHING);//7
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(7, result);
    }

    @Test
    public void testDurationForBrushing() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.BRUSHING);//20 min
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(20, result);
    }

    @Test
    public void testDurationForWashingAndBrushing() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.WASHING); //45
        procedures.add(Procedure.BRUSHING);//20-10=10 min after washing
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(55, result);
    }

    @Test
    public void testDurationForWashingAndFleasRemoving() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.WASHING); //45 
        procedures.add(Procedure.FLEAS_REMOVING);//5 min + 30min gap for fleas remover to sit
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(80, result);
    }

    @Test
    public void testDurationForWashingAndClawsCutting() {
        List<Procedure> procedures = new ArrayList<>();
        procedures.add(Procedure.WASHING); //45
        procedures.add(Procedure.CLAWS_CUTTING);//10-5=5 min after washing
        long result = durationService.getDurationForProcedures(procedures);
        
        assertEquals(50, result);
    }
}
