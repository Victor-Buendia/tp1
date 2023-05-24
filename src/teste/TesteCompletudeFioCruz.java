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
//            { testCases[1], 79.16 },
//            { testCases[9], 79.16 },
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
//        c.imprimeCampos();
        assertEquals(resultadoEsperado, c.calcularCompletudeMultiCampos(), 0.1);
    }
}