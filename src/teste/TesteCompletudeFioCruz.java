package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import completude.Completude;
import parser.JSONParser;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class TesteCompletudeFioCruz {
    @Parameters
    public static Collection<Object[]> getParameters() {
    	String filePath = "src/json/extratoFioCruzCorrigido.json";
    	Object[] testCases = JSONParser.parseJSONFile(filePath);
    	
        return Arrays.asList(new Object[][] {
            { testCases[0], 79.68 },
            { testCases[1], 79.16 },
            { testCases[2], 80.00 },
            { testCases[3], 78.12 },
            { testCases[4], 78.57 },
            { testCases[5], 81.25 },
            { testCases[6], 79.16 },
            { testCases[7], 75.96 },
            { testCases[8], 75.73 },
            { testCases[9], 76.25 },
        });
    }
    
    private Object[][] camposAninhados;
    private double resultadoEsperado;
    
    public TesteCompletudeFioCruz(Object[][] camposAninhados, double resultadoEsperado) {
        this.camposAninhados = camposAninhados;
        this.resultadoEsperado = resultadoEsperado;
    }
    
    @Test
    public void testCompletudeMultiCamposFioCruz() {
        Completude c = new Completude(camposAninhados);
        assertEquals(resultadoEsperado, c.calcularCompletudeMultiCampos(), 0.1);
    }
}