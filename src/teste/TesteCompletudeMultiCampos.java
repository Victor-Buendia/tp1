package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import completude.Completude;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class TesteCompletudeMultiCampos {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { new Object[][] {
                {"PrimeiroNome", "Nelso"},
                {"NomeMeio", null},
                {"UltimoNome", null}
            }, true },
            { new Object[][] {
                {"CPF", null},
                {"Matricula", "23/01234956"},
                {"Sexo", "M"},
                {"Email", null}
            }, true },
            { new Object[][] {
                {"CPF", "111.111.111-10"},
                {"Matricula", "23/01234956"},
                {"Sexo", "M"},
                {"Email", "nelsinho@unb.br"}
            }, true },
            { new Object[][] {
                {"CPF", null},
                {"Matricula", null},
                {"Sexo", null},
                {"Email", null}
            }, false },
            { new Object[][] {
                {"CPF", null},
                {"Matrícula", null},
                {"Sexo", null},
                {"Email", null},
                {"Nome", new Object[][] {
                    {"PrimeiroNome", null},
                    {"NomeMeio", null},
                    {"UltimoNome", null}
                }}
            }, false },
            { new Object[][] {
                {"CPF", null},
                {"Matrícula", null},
                {"Sexo", "Masculino"},
                {"Email", null},
                {"Nome", new Object[][] {
                    {"PrimeiroNome", null},
                    {"NomeMeio", null},
                    {"UltimoNome", null}
                }}
            }, true },
            { new Object[][] {
                {"CPF", null},
                {"Matrícula", null},
                {"Sexo", null},
                {"Email", null},
                {"Nome", new Object[][] {
                    {"PrimeiroNome", "Ana"},
                    {"NomeMeio", null},
                    {"UltimoNome", null}
                }}
            }, true }
        });
    }
    
    private Object[][] dados;
    private boolean resultadoEsperado;
    
    public TesteCompletudeMultiCampos(Object[][] dados, boolean resultadoEsperado) {
        this.dados = dados;
        this.resultadoEsperado = resultadoEsperado;
    }
    
    @Test
    public void testCompletudeOrInclusivo() {
        Completude c = new Completude(dados);
        assertEquals(resultadoEsperado, c.checarCompletudeOrInclusivo());
    }
}