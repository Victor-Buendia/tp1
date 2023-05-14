package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import completude.Completude;

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
		
		pessoaFisica.imprimeCampos();
	}
	
	// Calculo da completude de campos OR INCLUSIVO
	// Fazer a falsificação, duplicação e triangulação
	// Finalizar parametrizando os testes
	@Test
	public void test2() {
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
	public void test4() {
		fail("Exceção");
	}


}
