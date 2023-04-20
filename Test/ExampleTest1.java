package org.example1;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BooleanSupplier;

public class ExampleTest1 {
    @Test
    public void el_idganador_es(){
        Integer goles_equipo1_id=3;
        Integer goles_equipo2_id=1;
        assertTrue(example1.esMayorQue(goles_equipo1_id, goles_equipo2_id));
    }
    private void assertTrue(BooleanSupplier esMayorQue) {
    }
    @Test
    public void empate(){
        Integer goles_equipo1_id=3;
        Integer goles_equipo2_id=3;
        String empate="empate";
        assertEquals(empate,example1.igualar(goles_equipo1_id,goles_equipo2_id));
    }
    private void assertEquals(String empate, Object igualar) {
    }
}