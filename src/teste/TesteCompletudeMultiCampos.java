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

    @Test
    public void testCompletudeMultiCampos_Preenchidos() {
        Completude completude = new Completude(new Object[][] {
            {"CPF", "123456789-01"},
            {"Matricula", "123456789"},
            {"Sexo", "Masculino"},
            {"Email", "nelsinho@unb.br"}
        });

        double completudeEsperada = 100.0;
        double completudeCalculada = completude.calcularCompletudeMultiCampos();

        assertEquals(completudeEsperada, completudeCalculada, 0.01);
    }
    
    @Test
    public void testCompletudeMultiCampos_ParcialmentePreenchidos() {
        Completude completude = new Completude(new Object[][] {
            {"CPF", "123456789-01"},
            {"Matrícula", null},
            {"Sexo", "Masculino"},
            {"Email", null}
        });
    
        double completudeEsperada = 50.00; 
        double completudeCalculada = completude.calcularCompletudeMultiCampos();
    
        assertEquals(completudeEsperada, completudeCalculada, 0.01);
    }
    
    @Test
    public void testCompletudeMultiCampos_Vazio() {
        Completude completude = new Completude(new Object[][] {
            {"CPF", null},
            {"Matrícula", null},
            {"Sexo", null},
            {"Email", null}
        });
    
        double completudeEsperada = 0.0;
        double completudeCalculada = completude.calcularCompletudeMultiCampos();
    
        assertEquals(completudeEsperada, completudeCalculada, 0.01);
    }
    
    @Test
    public void testCompletudeMultiCampos_TodosPreenchidos() {
        Completude completude = new Completude(new Object[][] {
            {"CPF", "12345678900"},
            {"Matrícula", "123456789"},
            {"Sexo", "Masculino"},
            {"Email", "nelsinho@unb.br"}
        });
    
        double completudeEsperada = 100.0; 
        double completudeCalculada = completude.calcularCompletudeMultiCampos();
    
        assertEquals(completudeEsperada, completudeCalculada, 0.01);
    }
}