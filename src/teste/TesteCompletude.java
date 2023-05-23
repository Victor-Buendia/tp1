package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import completude.Completude;
import parser.JSONParser;

public class TesteCompletude {
	
	Completude pessoaFisica;
	
	@Test
	public void testCriacaoCampos() {
		pessoaFisica = new Completude(
			new Object[][] {
				{"CPF", null},
				{"Matrícula", null},
				{"Sexo", "M"},
				{"Email", null},
				{"Nome", new Object[][] {
					{"PrimeiroNome", "Victor"},
					{"NomeMeio", null},
					{"UltimoNome", null}
				}}
			}
		);
		
//		pessoaFisica.imprimeCampos();
	}
	
	// Calculo da completude de campos OR INCLUSIVO
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test2() {
		String filePath = "src/json/extratoFioCruzCorrigido.json";
		Object[] testCases = JSONParser.parseJSONFile(filePath);
		
		for (Object test : testCases) {
			Completude completude = new Completude((Object[][]) test);
			completude.imprimeCampos();
		}
		
		System.err.println(testCases.length);
		
		fail("Calculo da completude de campos OR INCLUSIVO");
	}

	// Cálculo da completude de registros multi-campos
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test3() {
		fail("Cálculo da completude de registros multi-campos");
	}

	// Testes de exceção
	// Não são parametrizados
	@Test
    public void testCompletude_CamposNulos() {
        assertThrows(IllegalArgumentException.class, () -> {
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
                    {"UltimoNome", "Lanna"}
                }}
            });
        });
    }


}
