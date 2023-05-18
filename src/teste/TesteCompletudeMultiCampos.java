package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import completude.Completude;

public class TesteCompletudeMultiCampos {
    @Test
    public void testCompletudeMultiCampos() {
        Completude completude = new Completude(new Object[][] {
            {"CPF", null},
            {"Matricula", null},
            {"Sexo", null},
            {"Email", null}
        });

        double completudeEsperada = 0.0; 
        double completudeCalculada = completude.calcularCompletudeMultiCampos();

        assertEquals(completudeEsperada, completudeCalculada, 0.01);
    }
}