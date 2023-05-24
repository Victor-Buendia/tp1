package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import completude.Completude;

public class TesteCompletude {
	
	Completude pessoaFisica;
	
	// Testes de exceção
	// Não são parametrizados
	@Test
    public void testCompletude_CamposNulos() {
        assertThrows(NullPointerException.class, () -> {
            pessoaFisica = new Completude(null);
        });
    }

    @Test
    public void testCompletude_ValorNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            pessoaFisica = new Completude(new Object[][] {
                {"CPF", "111.111.111-11"},
                {"Matrícula", "23/01234956"},
                {"Sexo", null},
                {"Email", "nelsinho@unb.br"},
                {null, "Test"}
            });
        });
    }

    @Test
    public void testCompletude_ValorAninhadoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            pessoaFisica = new Completude(new Object[][] {
                {"CPF", "111.111.111-11"},
                {"Matrícula", "23/01234956"},
                {"Sexo", "M"},
                {"Email", "nelsinho@unb.br"},
                {"Nome", new Object[][] {
                    {"PrimeiroNome", "Nelso"},
                    {"NomeMeio", null},
                    {null, "Lanna"}
                }}
            });
        });
    }


}
